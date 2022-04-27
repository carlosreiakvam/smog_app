package com.example.dte_2603_prosjekt.datasources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dte_2603_prosjekt.domain.models.Station

@Entity(tableName = "stations")
data class DatabaseStation(
    @PrimaryKey
    val eoi: String,
    val grunnkrets: String,
    val height: Long,
    val latitude: Long,
    val name: String,
    val delomrade: String,
    val longitude: Long,
    val kommune: String,


    )

@Entity(tableName = "grunnkrets")
data class Grunnkrets(
    @field:PrimaryKey val name: String,
    val areacode: Long
)

@Entity(tableName = "kommune")
data class Kommune(
    @field:PrimaryKey val name: String,
    val areacode: Long
)

@Entity(tableName = "delomrade")
data class Delomrade(
    @field:PrimaryKey val name: String,
    val areacode: Long
)


fun List<DatabaseStation>.asDomainDatapoints(): List<Station> {
    return map {
        Station(
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

enum class Areaclass {
    DELOMRAADE, FYLKE, GRUNNKRETS, KOMMUNE
}