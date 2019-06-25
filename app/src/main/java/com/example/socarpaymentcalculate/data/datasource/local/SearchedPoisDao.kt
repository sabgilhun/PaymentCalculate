package com.example.socarpaymentcalculate.data.datasource.local

import androidx.room.*
import com.example.socarpaymentcalculate.data.datasource.local.entity.PoisEntity
import io.reactivex.Single

@Dao
abstract class SearchedPoisDao {

    @Query("SELECT * FROM SEARCHED_POIS WHERE keyword = :keyword")
    abstract fun getSearchedPoisByKeyword(keyword: String): Single<PoisEntity>

    @Query("DELETE FROM SEARCHED_POIS WHERE keyword NOT IN (SELECT keyword FROM SEARCHED_POIS ORDER BY time DESC LIMIT 50)")
    abstract fun deleteOldestPois()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPois(poisEntity: PoisEntity)

    @Transaction
    open fun limitedInsertPois(poisEntity: PoisEntity) {
        insertPois(poisEntity)
        deleteOldestPois()
    }
}