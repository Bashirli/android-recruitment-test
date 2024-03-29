package com.bashirli.algoritmatask.data.repository.remote


import com.bashirli.algoritmatask.data.mapper.toInvestUiModel
import com.bashirli.algoritmatask.data.source.remote.RemoteDataSource
import com.bashirli.algoritmatask.domain.model.InvestUiModel
import com.bashirli.algoritmatask.domain.repository.RemoteRepository
import com.isteam.movieappaz.common.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val source: RemoteDataSource
) : RemoteRepository {
    override suspend fun connectSocket(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        source.connectSocket().collect {
            emit(it)
        }
    }

    override suspend fun receiveSocket(): Flow<Resource<InvestUiModel>> = flow {
        emit(Resource.Loading)
        source.receiveSocket().collect {
            when (it) {
                Resource.Loading -> Unit
                is Resource.Error -> emit(Resource.Error(it.throwable))
                is Resource.Success -> emit(Resource.Success(it.result.toInvestUiModel()))
                else -> Unit
            }
        }
    }

    override suspend fun disconnectSocket() = source.disconnectSocket()


}