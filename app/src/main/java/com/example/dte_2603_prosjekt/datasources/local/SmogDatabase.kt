package com.example.dte_2603_prosjekt.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dte_2603_prosjekt.datasources.local.entities.DatabaseAQIDescription
import com.example.dte_2603_prosjekt.datasources.local.entities.DatabaseStation
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
@Database(entities = [DatabaseStation::class, DatabaseAQIDescription::class], version = 2)
@TypeConverters(Converters::class)
abstract class SmogDatabase : RoomDatabase() {
    abstract val smogDao: SmogDao
}