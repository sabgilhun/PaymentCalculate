package com.example.socarpaymentcalculate.view.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class GoogleMapView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), OnMapReadyCallback {

    var googleMap: GoogleMap? = null

    var departurePointMarker: Marker? = null

    var destinationMarker: Marker? = null

    var routePolyline: Polyline? = null

    init {
        val mapFragment = SupportMapFragment.newInstance()

        if (!isInEditMode) {
            (context as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .add(id, mapFragment)
                .commit()
            mapFragment.getMapAsync(this)
        }
    }

    fun setDeparturePointMarker(latLng: LatLng) {
        departurePointMarker = departurePointMarker?.apply {
            position = latLng
        } ?: googleMap?.addMarker(MarkerOptions().position(latLng))
    }

    fun setDestinationMarker(latLng: LatLng) {
        destinationMarker = destinationMarker?.apply {
            position = latLng
        } ?: googleMap?.addMarker(MarkerOptions().position(latLng))
    }

    fun setRoutePolyline(route: List<LatLng>) {
        routePolyline = routePolyline?.apply {
            points = route
        } ?: googleMap?.addPolyline(PolylineOptions().addAll(route))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
    }
}