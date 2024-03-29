package com.bashirli.algoritmatask.data.source.remote

import com.bashirli.algoritmatask.data.dto.remote.InvestDTO
import com.isteam.movieappaz.common.network.Resource
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun connectSocket(): Flow<Resource<Boolean>>

    suspend fun receiveSocket(): Flow<Resource<InvestDTO>>

    suspend fun disconnectSocket()

}