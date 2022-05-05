package com.example.dte_2603_prosjekt.di

import com.example.dte_2603_prosjekt.datasources.IDataSource
import com.example.dte_2603_prosjekt.datasources.local.LocalSmogDatasource
import com.example.dte_2603_prosjekt.datasources.remote.RemoteSmogDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class LocalDatasource

@Qualifier
annotation class RemoteDatasource

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteDatasourceModule {

    @RemoteDatasource
    @Singleton
    @Binds
    abstract fun bindRemoteDatasource(impl: RemoteSmogDatasource): IDataSource
}


@InstallIn(SingletonComponent::class)
@Module
abstract class LocalDatasourceModule {

    @LocalDatasource
    @Singleton
    @Binds
    abstract fun bindLocalDatasource(impl: LocalSmogDatasource): IDataSource
}