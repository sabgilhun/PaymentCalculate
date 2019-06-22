package com.example.socarpaymentcalculate.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.socarpaymentcalculate.data.model.SearchedPois
import io.reactivex.Single

@Dao
interface SearchedPoisDao {

    @Query("SELECT * FROM SEARCHED_POIS WHERE keyword = :keyword")
    fun getSearchedPoisByKeyword(keyword: String): Single<SearchedPois>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchKeyword(searchedPois: SearchedPois): Single<Unit>

}