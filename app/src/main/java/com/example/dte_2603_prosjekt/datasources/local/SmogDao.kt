package com.example.dte_2603_prosjekt.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dte_2603_prosjekt.datasources.local.entities.DatabaseStation


@Dao
interface SmogDao {
    @Query("select * from stations")
    fun getStations(): List<DatabaseStation>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stations: List<DatabaseStation>): List<Long>
}