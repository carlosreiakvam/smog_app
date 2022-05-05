package com.example.dte_2603_prosjekt.datasources.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dte_2603_prosjekt.domain.model.Station
import com.example.dte_2603_prosjekt.domain.model.SubArea

@Entity(tableName = "stations")
data class DatabaseStation(
    @PrimaryKey
    val eoi: String,
    val name: String,
    val height: Double,
    val latitude: Double,
    val longitude: Double,
    @Embedded(prefix = "ground_area")
    val groundArea: DatabaseSubArea,
    @Embedded(prefix = "sub_area")
    val subarea: DatabaseSubArea,
    @Embedded(prefix = "municipality")
    val municipality: DatabaseSubArea,
)

data class DatabaseSubArea(
    val name: String,
    val areacode: String
) {
    fun asDomainSubArea(): SubArea {
        return SubArea(
            name = this.name,
            areacode = this.areacode
        )
    }
}


fun List<DatabaseStation>.asDomainStations(): List<Station> {
    return map {
        Station(
            groundArea = it.groundArea.asDomainSubArea(),
            height = it.height,
            eoi = it.eoi,
            latitude = it.latitude,
            name = it.name,
            subarea = it.subarea.asDomainSubArea(),
            longitude = it.longitude,
            municipality = it.municipality.asDomainSubArea(),
        )
    }
}