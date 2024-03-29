package com.bashirli.algoritmatask.domain.repository

import com.bashirli.algoritmatask.data.dto.local.InvestEntity
import com.bashirli.algoritmatask.domain.model.InvestResultUiModel
import com.isteam.movieappaz.common.network.Resource
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun insertAll(data: List<InvestEntity>)

    suspend fun update(data: List<InvestEntity>)

    suspend fun isDatabaseEmpty(): Flow<Resource<Boolean>>

    suspend fun getAll(): Flow<Resource<List<InvestResultUiModel>>>

}