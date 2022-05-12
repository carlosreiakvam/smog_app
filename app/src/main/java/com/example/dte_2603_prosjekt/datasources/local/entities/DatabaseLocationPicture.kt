package com.example.dte_2603_prosjekt.datasources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dte_2603_prosjekt.domain.model.LocationPicture

@Entity(tableName = "location_pictures")
data class DatabaseLocationPicture(
    @PrimaryKey
    val filename: String,
    val longitude: Double,
    val latitude: Double,
)


fun List<DatabaseLocationPicture>.asDomainLocationPicture(): List<LocationPicture> {
    return map {
        LocationPicture(
            filename = it.filename,
            longitude = it.longitude,
            latitude = it.latitude,
        )
    }
}