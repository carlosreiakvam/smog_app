package com.example.dte_2603_prosjekt.datasources.local

import com.example.dte_2603_prosjekt.datasources.IDataSource
import com.example.dte_2603_prosjekt.datasources.local.entities.asDomainAQIDescriptions
import com.example.dte_2603_prosjekt.datasources.local.entities.asDomainStations
import com.example.dte_2603_prosjekt.domain.model.AQIDescription
import com.example.dte_2603_prosjekt.domain.model.Station
import com.example.dte_2603_prosjekt.domain.model.asDatabaseAQIDescriptions
import com.example.dte_2603_prosjekt.domain.model.asDatabaseStations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocalSmogDatasource @Inject constructor(private val smogDao: SmogDao) : IDataSource {
    override suspend fun getStations(): Flow<List<Station>> = flow {
        val stations = smogDao.getStations()
        emit(stations.asDomainStations())
    }.flowOn(Dispatchers.IO)


    override suspend fun saveStations(stations: List<Station>): Flow<List<Long>> = flow {
        emit(smogDao.insertAllStations(stations.asDatabaseStations()))
    }.flowOn(Dispatchers.IO)

    override suspend fun getAQIDescriptions(): Flow<List<AQIDescription>> = flow {
        val descriptions = smogDao.getAQIDescriptions()
        emit(descriptions.asDomainAQIDescriptions())
    }.flowOn(Dispatchers.IO)

    override suspend fun saveAQIDescriptions(descriptions: List<AQIDescription>): Flow<List<Long>> =
        flow {
            emit(smogDao.insertAllAQIDescriptions(descriptions.asDatabaseAQIDescriptions()))
        }.flowOn(Dispatchers.IO)
}