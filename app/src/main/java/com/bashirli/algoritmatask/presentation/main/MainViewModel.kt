package com.bashirli.algoritmatask.presentation.main

import androidx.lifecycle.viewModelScope
import com.bashirli.algoritmatask.common.base.BaseViewModel
import com.bashirli.algoritmatask.common.base.State
import com.bashirli.algoritmatask.common.network.ConnectivityObserver
import com.bashirli.algoritmatask.common.network.NetworkConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkConnectivityObserver: NetworkConnectivityObserver
) : BaseViewModel<MainUiState>() {

    init {
        observeNetwork()
    }

    private fun observeNetwork() {
        viewModelScope.launch {
            networkConnectivityObserver.connectionObserve().collectLatest {
                setState(MainUiState.NetworkStatus(it))
            }
        }
    }

}

sealed class MainUiState : State {

    data class NetworkStatus(val status: ConnectivityObserver.ConnectionStatus) : MainUiState()

}