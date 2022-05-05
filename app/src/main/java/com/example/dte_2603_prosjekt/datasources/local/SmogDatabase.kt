package com.example.dte_2603_prosjekt.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dte_2603_prosjekt.datasources.local.entities.DatabaseStation

@Database(entities = [DatabaseStation::class], version = 1)
abstract class SmogDatabase : RoomDatabase() {
    abstract val smogDao: SmogDao
}