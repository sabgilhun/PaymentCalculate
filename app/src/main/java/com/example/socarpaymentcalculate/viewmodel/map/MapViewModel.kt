package com.example.socarpaymentcalculate.viewmodel.map

import com.example.socarpaymentcalculate.common.filterTo
import com.example.socarpaymentcalculate.data.TmapRepository
import com.example.socarpaymentcalculate.data.model.LatLngFactory
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.viewmodel.base.BaseViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class MapViewModel(private val repository: TmapRepository) : BaseViewModel() {

    private var departurePoint: Poi? = null

    private var destination: Poi? = null

    private val _departurePointMarkerPosition = PublishSubject.create<LatLng>()
    val departurePointMarkerPosition: Observable<LatLng>
        get() = _departurePointMarkerPosition

    private val _destinationMarkerPosition = PublishSubject.create<LatLng>()
    val destinationMarkerPosition: Observable<LatLng>
        get() = _destinationMarkerPosition

    private val _mapFocus = PublishSubject.create<LatLngBounds>()
    val mapFocus: Observable<LatLngBounds>
        get() = _mapFocus

    private val _route = PublishSubject.create<Route>()
    val route: Observable<Route>
        get() = _route

    private val _departurePointName = BehaviorSubject.create<String>()
    val departurePointName: Observable<String>
        get() = _departurePointName

    private val _destinationName = BehaviorSubject.create<String>()
    val destinationName: Observable<String>
        get() = _destinationName

    init {
        actionStream
            .filterTo(SetDeparturePointAction::class.java)
            .map { it.departurePoint }
            .subscribe(::setDeparture)
            .track()

        actionStream
            .filterTo(SetDestinationAction::class.java)
            .map { it.destination }
            .subscribe(::setDestination)
            .track()

        actionStream
            .filterTo(ClickSearchButtonAction::class.java)
            .subscribe { onClickSearch() }
            .track()
    }

    private fun onClickSearch() {
        departurePoint?.let { departurePoint ->
            destination?.let { destination ->
                repository.getRoutes(departurePoint, destination, ::calculateMapData) {}.track()
            }
        }
    }

    private fun setDeparture(departurePoint: Poi) {
        this.departurePoint = departurePoint
        _departurePointName.onNext(departurePoint.name)
    }

    private fun setDestination(destination: Poi) {
        this.destination = destination
        _destinationName.onNext(destination.name)
    }

    private fun calculateMapData(route: Route) {
        if (route.coordinates.isNotEmpty()) {

            _departurePointMarkerPosition.onNext(route.coordinates.first())
            _destinationMarkerPosition.onNext(route.coordinates.last())

            _mapFocus.onNext(
                LatLngBounds(
                    LatLngFactory.leftTop(route.coordinates),
                    LatLngFactory.rightBottom(route.coordinates)
                )
            )
            _route.onNext(route)
        }

    }
}