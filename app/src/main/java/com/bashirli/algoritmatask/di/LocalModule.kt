package com.bashirli.algoritmatask.di

import android.content.Context
import androidx.room.Room
import com.bashirli.algoritmatask.common.utils.Constants
import com.bashirli.algoritmatask.data.repository.local.LocalRepositoryImpl
import com.bashirli.algoritmatask.data.service.local.InvestDAO
import com.bashirli.algoritmatask.data.service.local.LocalService
import com.bashirli.algoritmatask.data.source.local.LocalDataSource
import com.bashirli.algoritmatask.data.source.local.LocalDataSourceImpl
import com.bashirli.algoritmatask.domain.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            LocalService::class.java,
            Constants.DATABASE
        ).build()

    @Singleton
    @Provides
    fun injectRoomDao(localService: LocalService) = localService.getDao()

    @Singleton
    @Provides
    fun injectLocalSource(dao: InvestDAO) = LocalDataSourceImpl(dao) as LocalDataSource

    @Singleton
    @Provides
    fun injectLocalRepo(source: LocalDataSource) = LocalRepositoryImpl(source) as LocalRepository

}