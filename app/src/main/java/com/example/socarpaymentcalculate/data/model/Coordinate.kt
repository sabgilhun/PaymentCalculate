package com.example.socarpaymentcalculate.data.model

data class Coordinate private constructor(
    val longitude: Double,
    val latitude: Double
) {
    companion object {

        fun with(longitude: Double, latitude: Double): Coordinate {
            return Coordinate(longitude, latitude)
        }

        fun center(p1: Coordinate, p2: Coordinate): Coordinate {
            return Coordinate(
                (p1.longitude + p2.longitude) / 2,
                (p1.latitude + p2.latitude) / 2
            )
        }

        fun leftTop(coordinates: List<Coordinate>): Coordinate? {
            if (coordinates.isNotEmpty()) {
                return Coordinate(
                    coordinates.map {
                        it.longitude
                    }.min()!!,
                    coordinates.map {
                        it.latitude
                    }.min()!!
                )
            }
            return null
        }

        fun rightBottom(coordinates: List<Coordinate>): Coordinate? {
            if (coordinates.isNotEmpty()) {
                return Coordinate(
                    coordinates.map {
                        it.longitude
                    }.max()!!,
                    coordinates.map {
                        it.latitude
                    }.max()!!
                )
            }
            return null
        }

    }
}