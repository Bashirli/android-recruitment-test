package com.bashirli.algoritmatask.data.service.remote

import android.util.Log
import com.bashirli.algoritmatask.data.dto.remote.InvestDTO
import com.google.gson.Gson
import com.isteam.movieappaz.common.network.Resource
import io.socket.client.Socket
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RemoteService @Inject constructor(private val socket: Socket) {

    suspend fun connectSocket(): Flow<Resource<Boolean>> = callbackFlow {
        trySend(Resource.Loading)
        socket.connect()
        socket.on(Socket.EVENT_DISCONNECT) {
            trySend(Resource.Success(false))
        }
        socket.on(Socket.EVENT_CONNECT_ERROR) {
            trySend(Resource.Error(Exception(it[0].toString())))
        }
        socket.on(Socket.EVENT_CONNECT) {
            trySend(Resource.Success(true))
        }
        awaitClose { socket.close() }
    }

    suspend fun receiveSocketData(): Flow<Resource<InvestDTO>> = callbackFlow {
        trySend(Resource.Loading)
        socket.connect()
        try {
            socket.on(io.socket.engineio.client.Socket.EVENT_MESSAGE) {
                val data = Gson().fromJson(
                    it[0].toString(),
                    InvestDTO::class.java
                )
                Log.e("test", " rsd $data")
                trySend(Resource.Success(data))
            }

        } catch (e: Exception) {
            trySend(Resource.Error(e))
        }
        awaitClose {
            trySend(Resource.Error(Exception("Error")))
            socket.close()
        }
    }

    fun disconnectSocket() {
        socket.close()
    }


}