package com.example.socarpaymentcalculate.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.socarpaymentcalculate.data.TmapRepository
import com.example.socarpaymentcalculate.data.model.Coordinate
import com.example.socarpaymentcalculate.data.model.CoordinateBlock
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.viewmodel.base.BaseViewModel

class MapViewModel(private val repository: TmapRepository) : BaseViewModel() {

    private val startPoint = MutableLiveData<Coordinate>()

    private val endPoint = MutableLiveData<Coordinate>()

    private val centerPoint = MutableLiveData<Coordinate>()

    private val mapFocus = MutableLiveData<CoordinateBlock>()

    private val routes = MutableLiveData<List<Coordinate>>()

    private var departure: Poi? = null

    private var destination: Poi? = null

    val departureName = MutableLiveData<String>()

    val destinationName = MutableLiveData<String>()

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

    fun setDeparture(departure: Poi) {
        this.departure = departure
        departureName.value = departure.name
    }

    fun setDestination(destination: Poi) {
        this.destination = destination
        destinationName.value = destination.name
    }

    private fun calculateMapData(route: Route) {

        if (route.coordinates.isNotEmpty()) {
            val leftTop = Coordinate.leftTop(route.coordinates)
            val rightBottom = Coordinate.rightBottom(route.coordinates)

            startPoint.value = route.coordinates.first()
            endPoint.value = route.coordinates.last()

            centerPoint.value = Coordinate.center(leftTop!!, rightBottom!!)
            mapFocus.value = CoordinateBlock.with(leftTop, rightBottom)

            routes.value = route.coordinates
        }

    }
}