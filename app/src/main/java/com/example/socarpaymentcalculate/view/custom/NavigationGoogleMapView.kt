package com.example.socarpaymentcalculate.view.custom

import android.content.Context
import android.util.AttributeSet
import com.example.socarpaymentcalculate.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*

class NavigationGoogleMapView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : GoogleMapView(context, attrs, defStyleAttr) {

    /* Attributes */
    private var departurePointMarkerIconId: Int = 0

    private var destinationMarkerIconId: Int = 0

    private var polylineColor: Int = DEFAULT_POLYLINE_COLOR

    private var polylineWith: Int = DEFAULT_POLYLINE_WIDTH

    /* Internal Values */
    private var departurePointMarker: Marker? = null

    private var destinationMarker: Marker? = null

    private var routePolyline: Polyline? = null

    init {
        if (attrs != null) {

            val typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.NavigationGoogleMapView)

            departurePointMarkerIconId = typedArray.getResourceId(
                R.styleable.NavigationGoogleMapView_departurePointMarkerIcon, 0
            )

            destinationMarkerIconId = typedArray.getResourceId(
                R.styleable.NavigationGoogleMapView_destinationMarkerIcon, 0
            )

            polylineColor = typedArray.getColor(
                R.styleable.NavigationGoogleMapView_polylineColor,
                DEFAULT_POLYLINE_COLOR
            )

            polylineWith = typedArray.getDimensionPixelSize(
                R.styleable.NavigationGoogleMapView_polylineWith,
                DEFAULT_POLYLINE_WIDTH
            )

            typedArray.recycle()
        }
    }

    fun setDeparturePointMarker(latLng: LatLng) {
        departurePointMarker = departurePointMarker?.apply {
            position = latLng
        } ?: googleMap?.addMarker(MarkerOptions().apply {
            position(latLng)
            BitmapDescriptorFactory.fromResource(departurePointMarkerIconId)?.let {
                icon(it)
            }
        })
    }

    fun setDestinationMarker(latLng: LatLng) {
        destinationMarker = destinationMarker?.apply {
            position = latLng
        } ?: googleMap?.addMarker(MarkerOptions().apply {
            position(latLng)
            BitmapDescriptorFactory.fromResource(destinationMarkerIconId)?.let {
                icon(it)
            }
        })
    }

    fun setRoutePolyline(route: List<LatLng>) {
        routePolyline = routePolyline?.apply {
            points = route
        } ?: googleMap?.addPolyline(PolylineOptions().apply {
            addAll(route)
            color(polylineColor)
            width(polylineWith.toFloat())
        })
    }

    fun setCameraFocus(latLngBounds: LatLngBounds?) {
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, CAMERA_FOCUS_BOUND_PADDING))
    }

    companion object {
        private const val CAMERA_FOCUS_BOUND_PADDING = 250

        private const val DEFAULT_POLYLINE_COLOR = 0x000000

        private const val DEFAULT_POLYLINE_WIDTH = 50
    }
}