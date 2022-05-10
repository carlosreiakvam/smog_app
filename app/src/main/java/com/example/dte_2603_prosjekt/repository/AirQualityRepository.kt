package com.example.dte_2603_prosjekt.repository

import com.example.dte_2603_prosjekt.datasources.IDataSource
import com.example.dte_2603_prosjekt.di.LocalDatasource
import com.example.dte_2603_prosjekt.di.RemoteDatasource
import com.example.dte_2603_prosjekt.domain.model.AQIDescription
import com.example.dte_2603_prosjekt.domain.model.Station
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class AirQualityRepository @Inject constructor(
    @RemoteDatasource
    private val remoteSmogDatasource: IDataSource,
    @LocalDatasource
    private val localSmogDatasource: IDataSource
) {

    suspend fun getStations() = localSmogDatasource.getStations()
    suspend fun getAQIDescriptions() = localSmogDatasource.getAQIDescriptions()

    suspend fun refreshStations(): Flow<Boolean> = flow {
        remoteSmogDatasource.getStations().collect { stations ->
            insertStationsInDb(stations).collect {
                emit(it)
            }
        }
    }

    suspend fun refreshAQIDescriptions(): Flow<Boolean> = flow {
        remoteSmogDatasource.getAQIDescriptions().collect { descriptions ->
            insertAQIDescriptionsInDB(descriptions).collect {
                emit(it)
            }
        }
    }

    private suspend fun insertStationsInDb(stations: List<Station>): Flow<Boolean> = flow {
        localSmogDatasource.saveStations(stations).collect {
            emit(it.isNotEmpty())
        }
    }

    private suspend fun insertAQIDescriptionsInDB(descriptions: List<AQIDescription>) = flow {
        localSmogDatasource.saveAQIDescriptions(descriptions).collect {
            emit(it.isNotEmpty())
        }
    }
}