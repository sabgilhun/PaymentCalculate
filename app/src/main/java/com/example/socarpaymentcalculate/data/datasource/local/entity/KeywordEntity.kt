package com.example.socarpaymentcalculate.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SEARCHED_KEYWORD")
data class KeywordEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "keyword")
    var searchKeyword: String,

    @ColumnInfo(name = "index")
    var index: Int,

    @ColumnInfo(name = "time")
    var timeStamp: Long
) {
    companion object {
        fun of(searchKeyword: String, index: Int): KeywordEntity {
            return KeywordEntity(
                searchKeyword,
                index,
                System.currentTimeMillis()
            )
        }
    }
}