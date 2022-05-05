package com.example.dte_2603_prosjekt.datasources.local

import com.example.dte_2603_prosjekt.datasources.IDataSource
import com.example.dte_2603_prosjekt.datasources.local.entities.asDomainStations
import com.example.dte_2603_prosjekt.domain.model.Station
import com.example.dte_2603_prosjekt.domain.model.asDatabaseStations
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow
import javax.inject.Inject

class LocalSmogDatasource @Inject constructor(private val smogDao: SmogDao) : IDataSource {
    override suspend fun getStations(): List<Station> {
        val stations = smogDao.getStations()
        return stations.asDomainStations()
    }


    override suspend fun saveStations(stations: List<Station>): List<Long> {
        return smogDao.insertAll(stations.asDatabaseStations())
    }
}