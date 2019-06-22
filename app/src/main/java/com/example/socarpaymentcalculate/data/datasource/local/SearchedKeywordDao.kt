package com.example.socarpaymentcalculate.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.socarpaymentcalculate.data.model.SearchedKeyword
import io.reactivex.Single

@Dao
interface SearchedKeywordDao {

    @Query("SELECT * FROM SEARCHED_KEYWORD WHERE keyword = :keyword")
    fun getSearchedKeywordByKeyword(keyword: String): Single<SearchedKeyword?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchedKeyword(searchedKeyword: SearchedKeyword): Single<Unit>

}