package com.example.socarpaymentcalculate.data.datasource.local

import androidx.room.*
import com.example.socarpaymentcalculate.data.datasource.local.entity.RouteEntity
import com.example.socarpaymentcalculate.data.model.Poi
import io.reactivex.Single

@Dao
abstract class RouteDao {

    @Query("SELECT * FROM SEARCHED_ROUTE WHERE poi_pair = :poiPair")
    abstract fun getRouteByPoiPair(poiPair: Pair<Poi, Poi>): Single<RouteEntity>

    @Query("DELETE FROM SEARCHED_ROUTE WHERE poi_pair NOT IN (SELECT poi_pair FROM SEARCHED_ROUTE ORDER BY time DESC LIMIT 10)")
    abstract fun deleteOldestRoute()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRoute(routeEntity: RouteEntity)

    @Delete
    abstract fun deleteRoute(routeEntity: RouteEntity)

    @Transaction
    open fun limitedInsertRoute(routeEntity: RouteEntity) {
        insertRoute(routeEntity)
        deleteOldestRoute()
    }
}