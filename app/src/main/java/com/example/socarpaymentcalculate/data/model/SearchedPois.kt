package com.example.socarpaymentcalculate.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SEARCHED_POIS")
data class SearchedPois constructor(
    @PrimaryKey @ColumnInfo(name = "keyword") var searchKeyword: String,
    @ColumnInfo(name = "pois") var pois: List<Poi>
) {
    companion object {
        fun of(searchKeyword: String, pois: List<Poi>): SearchedPois {
            return SearchedPois(
                searchKeyword,
                pois
            )
        }
    }
}