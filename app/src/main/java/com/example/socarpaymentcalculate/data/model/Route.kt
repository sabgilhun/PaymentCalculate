package com.example.socarpaymentcalculate.data.model

import com.example.socarpaymentcalculate.data.datasource.local.entity.RouteEntity
import com.example.socarpaymentcalculate.data.datasource.remote.response.RouteSearchResponse
import com.google.android.gms.maps.model.LatLng


data class Route private constructor(
    val totalDistance: Int,
    val totalTime: Int,
    val coordinates: List<LatLng>
) {

    companion object {
        private const val GEOMETRY_LINE_TYPE = "LineString"

        fun from(routeResponseSearchSearchResponse: RouteSearchResponse): Route {
            val features = routeResponseSearchSearchResponse.features

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
                totalDistance = (totalDistance / 1000.0).toInt(),
                totalTime = totalTime,
                coordinates = coordinates
            )
        }

        fun from(routeEntity: RouteEntity): Route {
            return Route(
                routeEntity.totalDistance,
                routeEntity.totalTime,
                routeEntity.coordinates
            )
        }
    }

}