package com.example.socarpaymentcalculate.data.datasource.remote.reqeust

import com.example.socarpaymentcalculate.data.model.Poi

data class RouteSearchRequestBody private constructor(
    val startLongitude: Double,
    val startLatitude: Double,
    val endLongitude: Double,
    val endLatitude: Double,
    val totalValue: Int
) {

    val fieldMap: HashMap<String, Any> = HashMap()

    init {
        fieldMap["startX"] = startLongitude
        fieldMap["startY"] = startLatitude
        fieldMap["endX"] = endLongitude
        fieldMap["endY"] = endLatitude
        fieldMap["totalValue"] = totalValue
    }

    companion object {
        fun of(startPoi: Poi, endPoi: Poi, totalValue: Boolean): RouteSearchRequestBody {
            return RouteSearchRequestBody(
                startLongitude = startPoi.longitude,
                startLatitude = startPoi.latitude,
                endLongitude = endPoi.longitude,
                endLatitude = endPoi.latitude,
                totalValue = if (totalValue) 1 else 0
            )
        }
    }

}