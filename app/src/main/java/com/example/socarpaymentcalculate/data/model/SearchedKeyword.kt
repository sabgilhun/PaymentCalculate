package com.example.socarpaymentcalculate.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SEARCHED_KEYWORD")
data class SearchedKeyword constructor(
    @PrimaryKey @ColumnInfo(name = "keyword") var searchKeyword: String,
    @ColumnInfo(name = "time") var timeStamp: Long
) {
    companion object {
        fun of(searchKeyword: String): SearchedKeyword {
            return SearchedKeyword(
                searchKeyword,
                System.currentTimeMillis()
            )
        }
    }
}