package com.example.socarpaymentcalculate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.socarpaymentcalculate.data.TmapRepository
import com.example.socarpaymentcalculate.data.model.LatLngFactory
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.viewmodel.base.BaseViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

class MapViewModel(private val repository: TmapRepository) : BaseViewModel() {

    private var departurePoint: Poi? = null

    private var destination: Poi? = null

    private val _departurePointMarkerPosition = MutableLiveData<LatLng>()
    val departurePointMarkerPosition: LiveData<LatLng>
        get() = _departurePointMarkerPosition

    private val _destinationMarkerPosition = MutableLiveData<LatLng>()
    val destinationMarkerPosition: LiveData<LatLng>
        get() = _destinationMarkerPosition

    private val _mapFocus = MutableLiveData<LatLngBounds>()
    val mapFocus: LiveData<LatLngBounds>
        get() = _mapFocus

    private val _route = MutableLiveData<Route>()
    val route: LiveData<Route>
        get() = _route

    private val _departurePointName = MutableLiveData<String>()
    val departurePointName: LiveData<String>
        get() = _departurePointName

    private val _destinationName = MutableLiveData<String>()
    val destinationName: LiveData<String>
        get() = _destinationName

    fun onClickSearch() {
        departurePoint?.let { departurePoint ->
            destination?.let { destination ->
                compositeDisposable.add(
                    repository.getRoutes(departurePoint, destination, {
                        calculateMapData(it)
                    }, {})
                )
            }
        }
    }

    fun setDeparture(departurePoint: Poi) {
        this.departurePoint = departurePoint
        _departurePointName.value = departurePoint.name
    }

    fun setDestination(destination: Poi) {
        this.destination = destination
        _destinationName.value = destination.name
    }

    private fun calculateMapData(route: Route) {
        if (route.coordinates.isNotEmpty()) {

            _departurePointMarkerPosition.value = route.coordinates.first()
            _destinationMarkerPosition.value = route.coordinates.last()

            _mapFocus.value = LatLngBounds(
                LatLngFactory.leftTop(route.coordinates),
                LatLngFactory.rightBottom(route.coordinates)
            )

            _route.value = route
        }

    }
}