package com.example.socarpaymentcalculate.data.model

import com.example.socarpaymentcalculate.data.remote.response.RouteSearchResponse
import com.google.android.gms.maps.model.LatLng

data class Route private constructor(
    val totalDistance: Int,
    val totalTime: Int,
    val expectedTaxiFare: Int,
    val coordinates: List<LatLng>

) {

    companion object {
        private const val GEOMETRY_LINE_TYPE = "LineString"

        fun from(routeSearchResponse: RouteSearchResponse): Route {
            val features = routeSearchResponse.features

            val totalDistance = features.mapNotNull { it.properties.totalDistance }.first()

            val totalTime = features.mapNotNull { it.properties.totalTime }.first()

            val taxiFare = features.mapNotNull { it.properties.taxiFare }.first()

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
                totalDistance = totalDistance,
                totalTime = totalTime,
                expectedTaxiFare = taxiFare,
                coordinates = coordinates
            )
        }
    }

}