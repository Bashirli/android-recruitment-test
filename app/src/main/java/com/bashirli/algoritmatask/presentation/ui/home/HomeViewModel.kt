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

    init {
        connectSocket()
    }

    private fun connectSocket() {
        viewModelScope.launch {
            useCase.connectSocket().handleResult(
                onComplete = {
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
                    setState(HomeUiState.Error(it.localizedMessage as String))
                }
            )
        }
    }

    fun receiveSocket() {
        viewModelScope.launch {
            useCase.receiveSocket().handleResult(
                onComplete = {
                    setState(HomeUiState.InvestData(it))
                },
                onLoading = {

                },
                onError = {
                    setState(HomeUiState.Error(it.localizedMessage as String))
                }
            )
        }
    }


}

sealed class HomeUiState : State {

    data object Loading : HomeUiState()

    data class Error(val message: String) : HomeUiState()

    data class InvestData(val data: InvestUiModel) : HomeUiState()

}