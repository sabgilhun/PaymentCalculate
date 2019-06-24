package com.example.socarpaymentcalculate.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.datasource.local.entity.RouteEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface RouteDao {

    @Query("SELECT * FROM SEARCHED_ROUTE WHERE poi_pair = :poiPair")
    fun getRouteByPoiPair(poiPair: Pair<Poi, Poi>): Single<RouteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoute(routeEntity: RouteEntity): Completable

}