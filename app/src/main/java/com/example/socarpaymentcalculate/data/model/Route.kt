package com.example.socarpaymentcalculate.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.socarpaymentcalculate.data.datasource.remote.response.RouteSearchResponse
import com.google.android.gms.maps.model.LatLng

@Entity(tableName = "SEARCHED_ROUTE")
data class Route constructor(
    @PrimaryKey @ColumnInfo(name = "poi_pair") var searchedPoisPair: Pair<Poi, Poi>,
    @ColumnInfo(name = "total_distance") var totalDistance: Int,
    @ColumnInfo(name = "total_time") var totalTime: Int,
    @ColumnInfo(name = "coordinates") var coordinates: List<LatLng>
) {

    companion object {
        private const val GEOMETRY_LINE_TYPE = "LineString"

        fun of(startPoi: Poi, endPois: Poi, routeSearchResponse: RouteSearchResponse): Route {
            val features = routeSearchResponse.features

            val totalDistance = features.mapNotNull { it.properties.totalDistance }.first()

            val totalTime = features.mapNotNull { it.properties.totalTime }.first()

            val coordinates =
                features.filter { it.geometry.type == GEOMETRY_LINE_TYPE }
                    .map {
                        it.geometry.coordinates.map { coordinate ->
                            val res = coordinate as ArrayList<*>
                            LatLngFactory.with(res[1] as Double, res[0] as Double)
                        }.toMutableList()
                    }.reduce { acc, mutableList ->
                        acc.addAll(mutableList)
                        acc
                    }.toList()

            return Route(
                searchedPoisPair = Pair(startPoi, endPois),
                totalDistance = (totalDistance / 1000.0).toInt(),
                totalTime = totalTime,
                coordinates = coordinates
            )
        }
    }

}