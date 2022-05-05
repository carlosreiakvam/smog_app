package com.example.dte_2603_prosjekt.repository

import com.example.dte_2603_prosjekt.datasources.IDataSource
import com.example.dte_2603_prosjekt.di.LocalDatasource
import com.example.dte_2603_prosjekt.di.RemoteDatasource
import com.example.dte_2603_prosjekt.domain.model.Station
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AirQualityRepository @Inject constructor(
    @RemoteDatasource
    private val remoteSmogDatasource: IDataSource,
    @LocalDatasource
    private val localSmogDatasource: IDataSource
) {

    suspend fun getStations(): Flow<List<Station>> = flow {
        localSmogDatasource.getStations().also {
            emit(it)
        }
    }

    suspend fun refreshStations(): Flow<Boolean> = flow {
        val stations = remoteSmogDatasource.getStations()
        insertStationsInDb(stations).collect {
            emit(it)
        }
    }

    private suspend fun insertStationsInDb(stations: List<Station>): Flow<Boolean> = flow {
        localSmogDatasource.saveStations(stations).also {
            emit(it.isNotEmpty())
        }
    }
}