package com.example.socarpaymentcalculate.data.model

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

data class Navigation(
    val startPointMarkerPosition: LatLng,
    val endPointMarkerPosition: LatLng,
    val mapFocus: LatLngBounds,
    val route: Route
)