package com.example.socarpaymentcalculate.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.socarpaymentcalculate.data.model.Poi

@Entity(tableName = "SEARCHED_POIS")
data class PoisEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "keyword")
    var searchKeyword: String,

    @ColumnInfo(name = "time")
    var time: Long,

    @ColumnInfo(name = "pois")
    var pois: List<Poi>
) {
    companion object {
        fun of(searchKeyword: String, pois: List<Poi>): PoisEntity {
            return PoisEntity(
                searchKeyword,
                System.currentTimeMillis(),
                pois
            )
        }
    }
}