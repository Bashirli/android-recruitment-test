package com.bashirli.algoritmatask.di

import com.bashirli.algoritmatask.common.utils.Constants
import com.bashirli.algoritmatask.data.repository.remote.RemoteRepositoryImpl
import com.bashirli.algoritmatask.data.service.remote.RemoteService
import com.bashirli.algoritmatask.data.source.remote.RemoteDataSource
import com.bashirli.algoritmatask.data.source.remote.RemoteDataSourceImpl
import com.bashirli.algoritmatask.domain.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket
import java.net.URI
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun injectSocket(): Socket {
        val options = IO.Options().apply {
            path = Constants.PATH
            transports = arrayOf(WebSocket.NAME)
        }
        return IO.socket(URI.create(Constants.BASE_URL), options)
    }

    @Singleton
    @Provides
    fun injectRemoteService(socket: Socket) = RemoteService(socket)

    @Singleton
    @Provides
    fun injectRemoteDataSource(remoteService: RemoteService) =
        RemoteDataSourceImpl(remoteService) as RemoteDataSource

    @Singleton
    @Provides
    fun injectRemoteRepository(remoteDataSource: RemoteDataSource) =
        RemoteRepositoryImpl(remoteDataSource) as RemoteRepository

}