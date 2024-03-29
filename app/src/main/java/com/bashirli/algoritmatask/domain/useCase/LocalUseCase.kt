package com.bashirli.algoritmatask.domain.useCase

import com.bashirli.algoritmatask.data.dto.local.InvestEntity
import com.bashirli.algoritmatask.domain.repository.LocalRepository
import javax.inject.Inject

class LocalUseCase @Inject constructor(private val repo: LocalRepository) {

    suspend fun insertAll(data: List<InvestEntity>) = repo.insertAll(data)

    suspend fun update(data: List<InvestEntity>) = repo.update(data)

    suspend fun isDatabaseEmpty() = repo.isDatabaseEmpty()

    suspend fun getAll() = repo.getAll()

}