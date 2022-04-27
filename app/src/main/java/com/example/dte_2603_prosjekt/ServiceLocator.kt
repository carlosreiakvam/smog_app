package com.example.dte_2603_prosjekt

import android.content.Context
//import com.example.oblig3_fotoalbum.datasources.local.PictureLocalDatasource
//import com.example.oblig3_fotoalbum.datasources.local.UsersDatabase
//import com.example.oblig3_fotoalbum.datasources.remote.PictureRemoteDatasource
//import com.example.oblig3_fotoalbum.network.PictureApiService
//import com.example.oblig3_fotoalbum.repository.PictureRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServiceLocator {
    //    var pictureRepository: PictureRepository? = null
    private const val BASE_URL = "http://10.239.120.166/weatherapi/airqualityforecast/0.1/"

//    fun provideUserRepository(context: Context): PictureRepository {
//        synchronized(this) {
//            return pictureRepository ?: createUserRepository(context)
//        }
//    }

//    private fun createUserRepository(context: Context): PictureRepository {
//        val newRepo =
//            PictureRepository(
//                createPostsLocalDataSource(context),
//                createRemoteDataSource(context)
//            )
//        pictureRepository = newRepo
//        return newRepo
//    }

//    private fun createPostsLocalDataSource(context: Context): PictureLocalDatasource {
//        return PictureLocalDatasource(UsersDatabase.getInstance(context))
//    }

//    private fun createRemoteDataSource(context: Context): PictureRemoteDatasource {
//        val moshi = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .build()
//        val retrofitService = retrofit.create(PictureApiService::class.java)
//        return PictureRemoteDatasource(retrofitService)
//    }
}
