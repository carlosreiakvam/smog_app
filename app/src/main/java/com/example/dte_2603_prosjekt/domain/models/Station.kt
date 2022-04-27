package com.example.dte_2603_prosjekt.domain.models

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.example.dte_2603_prosjekt.datasources.local.entities.DatabaseStation
import kotlinx.parcelize.Parcelize

@Parcelize
data class Station(
    val grunnkrets: String,
    val height: Long,
    val eoi: String,
    val latitude: Long,
    val name: String,
    val delomrade: String,
    val longitude: Long,
    val kommune: String

) : Parcelable


fun List<Station>.asDatabaseStations(): List<DatabaseStation> {
    return map {
        DatabaseStation(
            grunnkrets = it.grunnkrets,
            height = it.height,
            eoi = it.eoi,
            latitude = it.latitude,
            name = it.name,
            delomrade = it.delomrade,
            longitude = it.longitude,
            kommune = it.kommune,
        )
    }
}