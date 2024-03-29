package com.bashirli.algoritmatask.domain.repository

import com.bashirli.algoritmatask.domain.model.InvestUiModel
import com.isteam.movieappaz.common.network.Resource
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {


    suspend fun connectSocket(): Flow<Resource<Boolean>>

    suspend fun receiveSocket(): Flow<Resource<InvestUiModel>>

    suspend fun disconnectSocket()

}