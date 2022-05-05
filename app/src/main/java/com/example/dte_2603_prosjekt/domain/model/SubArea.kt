package com.example.dte_2603_prosjekt.domain.model

import android.os.Parcelable
import com.example.dte_2603_prosjekt.datasources.local.entities.DatabaseSubArea
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubArea(
    val name: String,
    val areacode: String
) : Parcelable {

    fun asDatabaseSubArea(): DatabaseSubArea {
        return DatabaseSubArea(
            name = name,
            areacode = areacode
        )
    }
}