package com.example.socarpaymentcalculate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.socarpaymentcalculate.data.TmapRepository
import com.example.socarpaymentcalculate.data.model.Coordinate
import com.example.socarpaymentcalculate.data.model.CoordinateBlock
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.viewmodel.base.BaseViewModel

class MapViewModel(private val repository: TmapRepository) : BaseViewModel() {

    private var departure: Poi? = null

    private var destination: Poi? = null

    private val _startPoint = MutableLiveData<Coordinate>()
    val startPoint: LiveData<Coordinate>
        get() = _startPoint

    private val _endPoint = MutableLiveData<Coordinate>()
    val endPoint: LiveData<Coordinate>
        get() = _endPoint

    private val _centerPoint = MutableLiveData<Coordinate>()
    val centerPoint: LiveData<Coordinate>
        get() = _centerPoint

    private val _mapFocus = MutableLiveData<CoordinateBlock>()
    val mapFocus: LiveData<CoordinateBlock>
        get() = _mapFocus

    private val _routes = MutableLiveData<List<Coordinate>>()
    val routes: LiveData<List<Coordinate>>
        get() = _routes

    private val _departurePointName = MutableLiveData<String>()
    val departurePointName: LiveData<String>
        get() = _departurePointName

    private val _destinationName = MutableLiveData<String>()
    val destinationName: LiveData<String>
        get() = _destinationName

    fun onClickSearch() {
        val departure = this.departure
        val destination = this.destination

        if (departure != null && destination != null) {
            compositeDisposable.add(
                repository.getRoutes(departure, destination,
                    { calculateMapData(it) },
                    {})
            )
        }
    }

    fun setDeparture(departurePoint: Poi) {
        this.departure = departurePoint
        _departurePointName.value = departurePoint.name
    }

    fun setDestination(destination: Poi) {
        this.destination = destination
        _destinationName.value = destination.name
    }

    private fun calculateMapData(route: Route) {
        if (route.coordinates.isNotEmpty()) {
            val leftTop = Coordinate.leftTop(route.coordinates)
            val rightBottom = Coordinate.rightBottom(route.coordinates)

            _startPoint.value = route.coordinates.first()
            _endPoint.value = route.coordinates.last()

            _centerPoint.value = Coordinate.center(leftTop!!, rightBottom!!)
            _mapFocus.value = CoordinateBlock.with(leftTop, rightBottom)

            _routes.value = route.coordinates
        }

    }
}