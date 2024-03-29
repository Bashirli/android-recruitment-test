package com.bashirli.algoritmatask.data.source.remote

import com.bashirli.algoritmatask.data.dto.remote.InvestDTO
import com.bashirli.algoritmatask.data.service.remote.RemoteService
import com.isteam.movieappaz.common.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val service: RemoteService
) : RemoteDataSource {
    override suspend fun connectSocket(): Flow<Resource<Boolean>> = service.connectSocket()

    override suspend fun receiveSocket(): Flow<Resource<InvestDTO>> = service.receiveSocketData()
    override suspend fun disconnectSocket() {
        service.disconnectSocket()
    }
}