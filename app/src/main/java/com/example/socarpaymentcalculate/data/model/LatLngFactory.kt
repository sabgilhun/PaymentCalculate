package com.example.socarpaymentcalculate.data.model

import com.google.android.gms.maps.model.LatLng

class LatLngFactory private constructor() {

    companion object {

        fun with(latitude: Double, longitude: Double): LatLng {
            return LatLng(latitude, longitude)
        }

        fun center(p1: LatLng, p2: LatLng): LatLng {
            return LatLng(
                (p1.latitude + p2.latitude) / 2,
                (p1.longitude + p2.longitude) / 2
            )
        }

        fun leftTop(coordinates: List<LatLng>): LatLng? {
            if (coordinates.isNotEmpty()) {
                return LatLng(
                    coordinates.map {
                        it.latitude
                    }.min()!!,
                    coordinates.map {
                        it.longitude
                    }.min()!!
                )
            }
            return null
        }

        fun rightBottom(coordinates: List<LatLng>): LatLng? {
            if (coordinates.isNotEmpty()) {
                return LatLng(
                    coordinates.map {
                        it.latitude
                    }.max()!!,
                    coordinates.map {
                        it.longitude
                    }.max()!!
                )
            }
            return null
        }
    }
}