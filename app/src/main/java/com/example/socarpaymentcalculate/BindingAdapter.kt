package com.example.socarpaymentcalculate

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.socarpaymentcalculate.adapter.CarModelAdapter
import com.example.socarpaymentcalculate.adapter.CarTypeAdapter
import com.example.socarpaymentcalculate.adapter.PoiAdapter
import com.example.socarpaymentcalculate.data.enums.CarModel
import com.example.socarpaymentcalculate.data.enums.CarType
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.view.custom.NavigationGoogleMapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

object BindingAdapter {
    @BindingAdapter("item_car_type")
    @JvmStatic
    fun setRecyclerViewCarTypeItem(view: RecyclerView, item: List<CarType>?) {
        if (item != null) {
            (view.adapter as CarTypeAdapter).submitList(item)
        }
    }

    @BindingAdapter("item")
    @JvmStatic
    fun setRecyclerViewPoiItem(view: RecyclerView, item: List<Poi>?) {
        if (item != null) {
            (view.adapter as PoiAdapter).submitList(item)
        }
    }

    @BindingAdapter("item_car_model")
    @JvmStatic
    fun setRecyclerViewCarModelItem(view: RecyclerView, item: List<CarModel>?) {
        if (item != null) {
            (view.adapter as CarModelAdapter).submitList(item)
        }
    }

    @BindingAdapter("departure_point")
    @JvmStatic
    fun setDeparturePointMarker(view: NavigationGoogleMapView, latLng: LatLng?) {
        latLng?.let {
            view.setDeparturePointMarker(latLng)
        }
    }

    @BindingAdapter("destination")
    @JvmStatic
    fun setDestination(view: NavigationGoogleMapView, latLng: LatLng?) {
        latLng?.let {
            view.setDestinationMarker(latLng)
        }
    }

    @BindingAdapter("camera_focus")
    @JvmStatic
    fun setCameraPosition(view: NavigationGoogleMapView, latLngBounds: LatLngBounds?) {
        latLngBounds?.let {
            view.setCameraFocus(latLngBounds)
        }
    }

    @BindingAdapter("route")
    @JvmStatic
    fun setRoutePolyline(view: NavigationGoogleMapView, route: List<LatLng>?) {
        route?.let {
            view.setRoutePolyline(route)
        }
    }
}
