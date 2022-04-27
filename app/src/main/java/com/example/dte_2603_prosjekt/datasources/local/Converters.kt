package com.example.dte_2603_prosjekt.datasources.local

import androidx.room.TypeConverter
import com.example.dte_2603_prosjekt.datasources.local.entities.Subarea
import com.example.dte_2603_prosjekt.datasources.local.entities.Groundarea
import com.example.dte_2603_prosjekt.datasources.local.entities.Municipality
import org.json.JSONObject

class Converters {

    @TypeConverter
    fun fromGroundarea(value: Groundarea): String {
        return JSONObject().apply {
            put("name", value.name)
            put("areacode", value.areacode)
        }.toString()
    }

    @TypeConverter
    fun toGroundarea(value: String): Groundarea {
        val json = JSONObject(value)
        return Groundarea(json.getString("name"), json.getLong("areacode"))
    }

    @TypeConverter
    fun fromMunicipality(value: Municipality): String {
        return JSONObject().apply {
            put("name", value.name)
            put("areacode", value.areacode)
        }.toString()
    }

    @TypeConverter
    fun toMunicipalitye(value: String): Municipality {
        val json = JSONObject(value)
        return Municipality(json.getString("name"), json.getLong("areacode"))
    }

    @TypeConverter
    fun fromSubarea(value: Subarea): String {
        return JSONObject().apply {
            put("name", value.name)
            put("areacode", value.areacode)
        }.toString()
    }

    @TypeConverter
    fun toSubarea(value: String): Subarea {
        val json = JSONObject(value)
        return Subarea(json.getString("name"), json.getLong("areacode"))
    }


}