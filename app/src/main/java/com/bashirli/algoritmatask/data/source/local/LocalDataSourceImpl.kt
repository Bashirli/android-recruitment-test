package com.bashirli.algoritmatask.data.source.local

import com.bashirli.algoritmatask.data.dto.local.InvestEntity
import com.bashirli.algoritmatask.data.service.local.InvestDAO
import com.isteam.movieappaz.common.network.Resource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val service: InvestDAO
) : LocalDataSource {
    override suspend fun insertAll(data: List<InvestEntity>) {
        service.insertAll(data)
    }

    override suspend fun update(data: List<InvestEntity>) {
        service.update(data)
    }

    override suspend fun isDatabaseEmpty(): Resource<Boolean> = handleResponse {
        service.isDatabaseEmpty()
    }

    override suspend fun getAll(): Resource<List<InvestEntity>> = handleResponse {
        service.getAll()
    }

    private suspend fun <T> handleResponse(response: suspend () -> T): Resource<T> {
        return try {
            val data = response.invoke()
            Resource.Success(data)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }


}