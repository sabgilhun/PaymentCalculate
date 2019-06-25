package com.example.socarpaymentcalculate.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import com.google.android.gms.maps.model.LatLng

@Entity(tableName = "SEARCHED_ROUTE")
data class RouteEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "poi_pair")
    var searchedPoisPair: Pair<Poi, Poi>,

    @ColumnInfo(name = "time")
    var time: Long,

    @ColumnInfo(name = "total_distance")
    var totalDistance: Int,

    @ColumnInfo(name = "total_time")
    var totalTime: Int,

    @ColumnInfo(name = "coordinates")
    var coordinates: List<LatLng>
) {

    companion object {

        fun of(startPoi: Poi, endPois: Poi, route: Route): RouteEntity {
            return RouteEntity(
                searchedPoisPair = Pair(startPoi, endPois),
                time = System.currentTimeMillis(),
                totalDistance = route.totalDistance,
                totalTime = route.totalTime,
                coordinates = route.coordinates
            )
        }
    }
}