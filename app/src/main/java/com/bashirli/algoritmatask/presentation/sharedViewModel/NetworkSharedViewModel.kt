package com.bashirli.algoritmatask.presentation.sharedViewModel

import com.bashirli.algoritmatask.common.base.BaseViewModel
import com.bashirli.algoritmatask.common.base.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NetworkSharedViewModel @Inject constructor() : BaseViewModel<NetworkSharedUiModel>() {

    private var isOnline = false

    fun setNetworkState(isOnline: Boolean) {
        this.isOnline = isOnline
        setState(NetworkSharedUiModel.IsOnline(isOnline))
    }

}

sealed class NetworkSharedUiModel : State {

    data class IsOnline(val isOnline: Boolean) : NetworkSharedUiModel()

}