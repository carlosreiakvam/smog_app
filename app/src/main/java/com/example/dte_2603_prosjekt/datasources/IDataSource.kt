package com.example.dte_2603_prosjekt.datasources

import com.example.dte_2603_prosjekt.domain.model.Station
import kotlinx.coroutines.flow.Flow

interface IDataSource {
    suspend fun getStations(): List<Station>

    suspend fun saveStations(stations: List<Station>): List<Long>
}