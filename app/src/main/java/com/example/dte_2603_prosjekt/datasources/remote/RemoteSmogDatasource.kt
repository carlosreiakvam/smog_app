package com.example.dte_2603_prosjekt.datasources.remote

import com.example.dte_2603_prosjekt.datasources.IDataSource
import com.example.dte_2603_prosjekt.datasources.remote.models.asDomainStations
import com.example.dte_2603_prosjekt.domain.model.AQIDescription
import com.example.dte_2603_prosjekt.domain.model.Station
import com.example.dte_2603_prosjekt.network.AirQualityApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class RemoteSmogDatasource @Inject constructor(private val airQualityApiService: AirQualityApiService) :
    IDataSource {
    override suspend fun getStations(): Flow<List<Station>> = flow {
        Timber.d("Started getting stations")
        val stations = airQualityApiService.getStations()
        emit(stations.asDomainStations())
    }.flowOn(Dispatchers.IO)

    override suspend fun saveStations(stations: List<Station>): Flow<List<Long>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAQIDescriptions(): Flow<List<AQIDescription>> = flow {
        Timber.d("Started")
        val aqiDesc = airQualityApiService.getAQIDescription()
        emit(aqiDesc.asDomainObject())
    }.flowOn(Dispatchers.IO)

    override suspend fun saveAQIDescriptions(descriptions: List<AQIDescription>): Flow<List<Long>> {
        TODO("Not yet implemented")
    }


}