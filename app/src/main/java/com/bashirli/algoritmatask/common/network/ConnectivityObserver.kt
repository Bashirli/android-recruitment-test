package com.bashirli.algoritmatask.common.network

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun connectionObserve(): Flow<ConnectionStatus>

    enum class ConnectionStatus {
        Available, Unavailable, Losing, Lost
    }

}