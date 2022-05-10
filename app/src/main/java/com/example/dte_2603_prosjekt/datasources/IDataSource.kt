package com.example.dte_2603_prosjekt.datasources


import com.example.dte_2603_prosjekt.domain.model.AQIDescription
import com.example.dte_2603_prosjekt.domain.model.Station
import kotlinx.coroutines.flow.Flow

interface IDataSource {
    suspend fun getStations(): Flow<List<Station>>
    suspend fun saveStations(stations: List<Station>): Flow<List<Long>>
    suspend fun getAQIDescriptions(): Flow<List<AQIDescription>>
    suspend fun saveAQIDescriptions(descriptions: List<AQIDescription>): Flow<List<Long>>
}