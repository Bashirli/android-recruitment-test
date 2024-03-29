package com.bashirli.algoritmatask.data.repository.local

import com.bashirli.algoritmatask.data.dto.local.InvestEntity
import com.bashirli.algoritmatask.data.mapper.toInvestListLocalUiModel
import com.bashirli.algoritmatask.data.source.local.LocalDataSource
import com.bashirli.algoritmatask.domain.model.InvestResultUiModel
import com.bashirli.algoritmatask.domain.repository.LocalRepository
import com.isteam.movieappaz.common.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val source: LocalDataSource
) : LocalRepository {
    override suspend fun insertAll(data: List<InvestEntity>) {
        source.insertAll(data)
    }

    override suspend fun update(data: List<InvestEntity>) {
        source.update(data)
    }

    override suspend fun isDatabaseEmpty(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        when (val response = source.isDatabaseEmpty()) {
            is Resource.Error -> emit(Resource.Error(response.throwable))
            Resource.Loading -> Unit
            is Resource.Success -> emit(Resource.Success(response.result))
        }
    }

    override suspend fun getAll(): Flow<Resource<List<InvestResultUiModel>>> = flow {
        emit(Resource.Loading)
        when (val response = source.getAll()) {
            is Resource.Error -> emit(Resource.Error(response.throwable))
            Resource.Loading -> Unit
            is Resource.Success -> emit(Resource.Success(response.result?.toInvestListLocalUiModel()))
        }
    }


}