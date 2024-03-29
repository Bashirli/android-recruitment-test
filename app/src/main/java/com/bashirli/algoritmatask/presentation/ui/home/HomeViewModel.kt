package com.bashirli.algoritmatask.presentation.ui.home

import androidx.lifecycle.viewModelScope
import com.bashirli.algoritmatask.common.base.BaseViewModel
import com.bashirli.algoritmatask.common.base.State
import com.bashirli.algoritmatask.domain.model.InvestUiModel
import com.bashirli.algoritmatask.domain.useCase.RemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: RemoteUseCase
) : BaseViewModel<HomeUiState>() {

    private var isErrorMessageSame = false

    init {
        connectSocket()
    }

    private fun connectSocket() {
        viewModelScope.launch {
            useCase.connectSocket().handleResult(
                onComplete = {
                    isErrorMessageSame = false
                    setState(HomeUiState.IsOnline(it))
                    if (it) {
                        receiveSocket()
                    } else {
                        //from offline
                    }
                },
                onLoading = {
                    setState(HomeUiState.Loading)
                },
                onError = {
                    if (!isErrorMessageSame) {
                        setState(
                            HomeUiState.Error(
                                it.localizedMessage as String,
                                isErrorMessageSame
                            )
                        )
                        setState(HomeUiState.IsOnline(false))
                        isErrorMessageSame = true
                    }
                }
            )
        }
    }

    private fun receiveSocket() {
        viewModelScope.launch {
            useCase.receiveSocket().handleResult(
                onComplete = {
                    setState(HomeUiState.InvestData(it))
                },
                onLoading = {
                },
                onError = {
                    isErrorMessageSame = false
                    setState(HomeUiState.Error(it.localizedMessage as String, isErrorMessageSame))
                }
            )
        }
    }

    private fun disconnect() {
        viewModelScope.launch {
            useCase.disconnectSocket()
        }
    }

    override fun onCleared() {
        super.onCleared()
        disconnect()
    }

}

sealed class HomeUiState : State {

    data object Loading : HomeUiState()

    data class Error(val message: String, val isSame: Boolean) : HomeUiState()

    data class IsOnline(val isOnline: Boolean) : HomeUiState()

    data class InvestData(val data: InvestUiModel) : HomeUiState()

}