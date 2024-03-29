package com.bashirli.algoritmatask.domain.useCase

import com.bashirli.algoritmatask.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteUseCase @Inject constructor(private val repo: RemoteRepository) {

    suspend fun connectSocket() = repo.connectSocket()

    suspend fun receiveSocket() = repo.receiveSocket()

    suspend fun disconnectSocket() = repo.disconnectSocket()

}