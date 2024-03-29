package com.bashirli.algoritmatask.data.source.local

import com.bashirli.algoritmatask.data.dto.local.InvestEntity
import com.isteam.movieappaz.common.network.Resource

interface LocalDataSource {

    suspend fun insertAll(data: List<InvestEntity>)

    suspend fun update(data: List<InvestEntity>)

    suspend fun isDatabaseEmpty(): Resource<Boolean>

    suspend fun getAll(): Resource<List<InvestEntity>>

}