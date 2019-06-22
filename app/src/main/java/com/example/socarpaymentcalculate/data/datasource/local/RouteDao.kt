package com.example.socarpaymentcalculate.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import io.reactivex.Single

@Dao
interface RouteDao {

    @Query("SELECT * FROM SEARCHED_ROUTE WHERE poi_pair = :poiPair")
    fun getRouteByPoiPair(poiPair: Pair<Poi, Poi>): Single<Route>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoute(route: Route): Single<Unit>

}