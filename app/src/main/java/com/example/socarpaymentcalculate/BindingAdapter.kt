package com.example.socarpaymentcalculate

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.socarpaymentcalculate.adapter.PoiAdapter
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.view.custom.GoogleMapView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

object BindingAdapter {
    @BindingAdapter("item")
    @JvmStatic
    fun setRecyclerViewItem(view: RecyclerView, item: List<Poi>?) {
        if (item != null) {
            (view.adapter as PoiAdapter).submitList(item)
        }
    }

    @BindingAdapter("departure_point")
    @JvmStatic
    fun setDeparturePointMarker(view: GoogleMapView, latLng: LatLng?) {
        latLng?.let {
            view.setDeparturePointMarker(latLng)
        }
    }

    @BindingAdapter("destination")
    @JvmStatic
    fun setDestination(view: GoogleMapView, latLng: LatLng?) {
        latLng?.let {
            view.setDestinationMarker(latLng)
        }
    }

    @BindingAdapter("camera_focus")
    @JvmStatic
    fun setCameraPosition(view: GoogleMapView, latLngBounds: LatLngBounds?) {
        latLngBounds?.let {
            view.googleMap?.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 300))
        }
    }

    @BindingAdapter("route")
    @JvmStatic
    fun setRoutePolyline(view: GoogleMapView, route: List<LatLng>?) {
        route?.let {
            view.setRoutePolyline(route)
        }
    }
}
