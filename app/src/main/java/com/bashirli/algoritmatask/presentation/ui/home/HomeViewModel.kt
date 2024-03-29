package com.bashirli.algoritmatask.presentation.ui.home

import androidx.lifecycle.viewModelScope
import com.bashirli.algoritmatask.common.base.BaseViewModel
import com.bashirli.algoritmatask.common.base.State
import com.bashirli.algoritmatask.data.dto.local.InvestEntity
import com.bashirli.algoritmatask.data.mapper.toLocalDatabaseList
import com.bashirli.algoritmatask.domain.model.InvestResultUiModel
import com.bashirli.algoritmatask.domain.model.InvestUiModel
import com.bashirli.algoritmatask.domain.useCase.LocalUseCase
import com.bashirli.algoritmatask.domain.useCase.RemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: RemoteUseCase,
    private val localUseCase: LocalUseCase
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
                        getOfflineData()
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

    fun getOfflineData() {
        viewModelScope.launch {
            localUseCase.getAll().handleResult(
                onComplete = {
                    setState(HomeUiState.OfflineData(it))
                },
                onLoading = {
                    setState(HomeUiState.Loading)
                },
                onError = {
                    isErrorMessageSame = false
                    setState(HomeUiState.Error(it.localizedMessage as String, isErrorMessageSame))
                }
            )
        }
    }

    fun updateDatabase(data: List<InvestResultUiModel>) {
        viewModelScope.launch {
            val newData = data.toLocalDatabaseList()
            localUseCase.isDatabaseEmpty().handleResult(
                onComplete = {
                    prepareDatabase(newData, it)
                },
                onLoading = {
                    setState(HomeUiState.Loading)
                },
                onError = {
                    isErrorMessageSame = false
                    setState(HomeUiState.Error(it.localizedMessage as String, isErrorMessageSame))
                }
            )
        }
    }

    private fun prepareDatabase(data: List<InvestEntity>, isEmpty: Boolean) {
        viewModelScope.launch {
            if (isEmpty) {
                localUseCase.insertAll(data)
            } else {
                localUseCase.update(data)
            }
            setState(HomeUiState.DatabaseEdit)
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

    data object DatabaseEdit : HomeUiState()

    data class OfflineData(val data: List<InvestResultUiModel>) : HomeUiState()

}