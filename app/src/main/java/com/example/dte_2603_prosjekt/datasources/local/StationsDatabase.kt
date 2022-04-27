package com.example.dte_2603_prosjekt.datasources.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dte_2603_prosjekt.datasources.local.entities.DatabaseStation

@Database(entities = [DatabaseStation::class], version = 1)
abstract class StationsDatabase: RoomDatabase() {
    abstract val stationDao: StationsDao

    companion object    {
        @Volatile
        private var INSTANCE: StationsDatabase? = null
        fun getInstance(context: Context): StationsDatabase   {
            synchronized(this)  {
                var instance = INSTANCE

                if (instance == null)   {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StationsDatabase::class.java,
                        "stations"

                    ). fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}