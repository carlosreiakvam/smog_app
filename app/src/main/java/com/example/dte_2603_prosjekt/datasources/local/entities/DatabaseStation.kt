package com.example.dte_2603_prosjekt.datasources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dte_2603_prosjekt.domain.models.Station

@Entity(tableName = "stations")
data class DatabaseStation(
    @PrimaryKey
    val eoi: String,
    val groundarea: Groundarea,
    val height: Long,
    val latitude: Long,
    val name: String,
    val subarea: Subarea,
    val longitude: Long,
    val municipality: Municipality,


    )

@Entity(tableName = "groundarea")
data class Groundarea(
    @field:PrimaryKey val name: String,
    val areacode: Long
)

@Entity(tableName = "municipality")
data class Municipality(
    @field:PrimaryKey val name: String,
    val areacode: Long
)

@Entity(tableName = "subarea")
data class Subarea(
    @field:PrimaryKey val name: String,
    val areacode: Long
)


fun List<DatabaseStation>.asDomainStations(): List<Station> {
    return map {
        Station(
            groundarea = it.groundarea,
            height = it.height,
            eoi = it.eoi,
            latitude = it.latitude,
            name = it.name,
            subarea = it.subarea,
            longitude = it.longitude,
            municipality = it.municipality,
        )
    }
}