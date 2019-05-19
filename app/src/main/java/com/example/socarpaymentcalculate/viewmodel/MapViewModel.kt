package com.example.socarpaymentcalculate.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.socarpaymentcalculate.data.model.Coordinate
import com.example.socarpaymentcalculate.data.model.CoordinateBlock
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.viewmodel.base.BaseViewModel

class MapViewModel : BaseViewModel() {

    private val startPoint = MutableLiveData<Coordinate>()

    private val endPoint = MutableLiveData<Coordinate>()

    private val centerPoint = MutableLiveData<Coordinate>()

    private val mapFocus = MutableLiveData<CoordinateBlock>()

    private val routes = MutableLiveData<List<Coordinate>>()

    fun calculateMapData(route: Route) {

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