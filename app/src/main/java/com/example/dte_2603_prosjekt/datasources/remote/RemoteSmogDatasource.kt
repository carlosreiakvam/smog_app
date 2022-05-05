package com.example.dte_2603_prosjekt.datasources.remote

import com.example.dte_2603_prosjekt.datasources.IDataSource
import com.example.dte_2603_prosjekt.datasources.remote.models.asDomainStations
import com.example.dte_2603_prosjekt.domain.model.Station
import com.example.dte_2603_prosjekt.network.AirQualityApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteSmogDatasource @Inject constructor(private val airQualityApiService: AirQualityApiService) :
    IDataSource {
    override suspend fun getStations(): List<Station> {
        val stations = airQualityApiService.getStations()
        return stations.asDomainStations()
    }

    override suspend fun saveStations(stations: List<Station>): List<Long> {
        TODO("Not yet implemented")
    }
}