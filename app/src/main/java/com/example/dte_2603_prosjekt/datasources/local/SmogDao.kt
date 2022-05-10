package com.example.dte_2603_prosjekt.datasources.local

import androidx.room.*
import com.example.dte_2603_prosjekt.datasources.local.entities.DatabaseAQIDescription
import com.example.dte_2603_prosjekt.datasources.local.entities.DatabaseStation


@Dao
interface SmogDao {
    @Query("select * from stations")
    fun getStations(): List<DatabaseStation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllStations(stations: List<DatabaseStation>): List<Long>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAQIDescriptions(descriptions: List<DatabaseAQIDescription>): List<Long>

    @Transaction
    @Query("select * from aqi_description")
    fun getAQIDescriptions(): List<DatabaseAQIDescription>
}