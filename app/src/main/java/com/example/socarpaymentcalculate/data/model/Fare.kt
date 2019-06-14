package com.example.socarpaymentcalculate.data.model

import com.example.socarpaymentcalculate.data.enums.CarModel

data class Fare private constructor(
    val carModel: CarModel,
    val totalTime: Int,
    val totalDistance: Int,
    val totalFareWeekday: Int,
    val totalFareWeekend: Int
) {

    companion object {
        fun of(carModel: CarModel, route: Route, totalFareWeekday: Int, totalFareWeekend: Int): Fare {
            return Fare(
                carModel,
                (route.totalTime / 60),
                route.totalDistance,
                totalFareWeekday,
                totalFareWeekend
            )
        }
    }
}