package com.example.dte_2603_prosjekt.di

import com.example.dte_2603_prosjekt.network.AirQualityApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * A Hilt module is a class that is annotated with @Module..
 *
 * Merk: BRUKER ViewModelComponent OG @ViewModelScoped slik at instansen følger syklusen
 * til viewmodel-objektet.
 */
@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideAirQualityService(retrofit: Retrofit): AirQualityApiService {
        return retrofit.create(AirQualityApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    companion object {
        private const val BASE_URL = "http://10.239.120.166/weatherapi/airqualityforecast/0.1/"
    }
}
