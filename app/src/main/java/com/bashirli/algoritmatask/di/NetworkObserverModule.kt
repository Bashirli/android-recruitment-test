package com.bashirli.algoritmatask.di

import android.content.Context
import android.net.ConnectivityManager
import com.bashirli.algoritmatask.common.network.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkObserverModule {

    @Singleton
    @Provides
    fun injectConnectivityManager(@ApplicationContext context: Context) = context.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

    @Singleton
    @Provides
    fun injectNetworkConnectivityObserver(connectivityManager: ConnectivityManager) =
        NetworkConnectivityObserver(connectivityManager)

}