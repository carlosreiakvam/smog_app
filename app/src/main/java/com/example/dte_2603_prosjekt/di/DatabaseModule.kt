package com.example.dte_2603_prosjekt.di

import android.content.Context
import androidx.room.Room
import com.example.dte_2603_prosjekt.datasources.local.SmogDao
import com.example.dte_2603_prosjekt.datasources.local.SmogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideSmogDao(database: SmogDatabase): SmogDao {
        return database.smogDao
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): SmogDatabase {
        return Room.databaseBuilder(
            appContext,
            SmogDatabase::class.java,
            "smog.db"
        ).build()
    }
}