package com.example.socarpaymentcalculate.view.custom

import android.content.Context
import android.util.AttributeSet
import com.example.socarpaymentcalculate.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*

class NavigationGoogleMapView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : GoogleMapView(context, attrs, defStyleAttr) {

    /* Attributes */
    private var startPointMarkerIconId: Int = 0

    private var endPointMarkerIconId: Int = 0

    private var polylineColor: Int = DEFAULT_POLYLINE_COLOR

    private var polylineWith: Int = DEFAULT_POLYLINE_WIDTH

    /* Internal Values */
    private var startPointMarker: Marker? = null

    private var endPointMarker: Marker? = null

    private var routePolyline: Polyline? = null

    /* Listeners */
    private var cameraMoveListener: (() -> Unit)? = null

    init {
        if (attrs != null) {

            val typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.NavigationGoogleMapView)

            startPointMarkerIconId = typedArray.getResourceId(
                R.styleable.NavigationGoogleMapView_startPointMarkerIcon, 0
            )

            endPointMarkerIconId = typedArray.getResourceId(
                R.styleable.NavigationGoogleMapView_endPointMarkerIcon, 0
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

    override fun onMapReady(googleMap: GoogleMap) {
        super.onMapReady(googleMap)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(37.576209, 126.976817), 16.0f))
        googleMap.setOnCameraMoveListener(cameraMoveListener)
    }

    fun setStartPointMarker(latLng: LatLng) {
        startPointMarker = startPointMarker?.apply {
            position = latLng
        } ?: googleMap?.addMarker(MarkerOptions().apply {
            position(latLng)
            BitmapDescriptorFactory.fromResource(startPointMarkerIconId)?.let {
                icon(it)
            }
        })
    }

    fun setEndPointMarker(latLng: LatLng) {
        endPointMarker = endPointMarker?.apply {
            position = latLng
        } ?: googleMap?.addMarker(MarkerOptions().apply {
            position(latLng)
            BitmapDescriptorFactory.fromResource(endPointMarkerIconId)?.let {
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

    fun setOnCameraMoveListener(listener: () -> Unit) {
        cameraMoveListener = listener
        googleMap?.setOnCameraMoveListener(listener)
    }

    companion object {
        private const val CAMERA_FOCUS_BOUND_PADDING = 250

        private const val DEFAULT_POLYLINE_COLOR = 0x000000

        private const val DEFAULT_POLYLINE_WIDTH = 50
    }
}