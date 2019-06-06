package com.example.socarpaymentcalculate.viewmodel.map

import com.example.socarpaymentcalculate.common.filterTo
import com.example.socarpaymentcalculate.common.setNetworkingThread
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

    private var startPoint: Poi? = null

    private var endPoint: Poi? = null

    private val _startPointMarkerPosition = PublishSubject.create<LatLng>()
    val startPointMarkerPosition: Observable<LatLng>
        get() = _startPointMarkerPosition

    private val _endPointMarkerPosition = PublishSubject.create<LatLng>()
    val endPointMarkerPosition: Observable<LatLng>
        get() = _endPointMarkerPosition

    private val _mapFocus = PublishSubject.create<LatLngBounds>()
    val mapFocus: Observable<LatLngBounds>
        get() = _mapFocus

    private val _route = PublishSubject.create<Route>()
    val route: Observable<Route>
        get() = _route

    private val _startPointName = BehaviorSubject.create<String>()
    val startPointName: Observable<String>
        get() = _startPointName

    private val _endPointName = BehaviorSubject.create<String>()
    val endPointName: Observable<String>
        get() = _endPointName

    init {
        actionStream
            .filterTo(SetStartPointAction::class.java)
            .map { it.startPoint }
            .subscribe(::setStartPoint)
            .track()

        actionStream
            .filterTo(SetEndPointAction::class.java)
            .map { it.endPoint }
            .subscribe(::setEndPoint)
            .track()

        actionStream
            .filterTo(ClickSearchButtonAction::class.java)
            .subscribe { onClickSearch() }
            .track()
    }

    private fun onClickSearch() {
        startPoint?.let { startPoint ->
            endPoint?.let { endPoint ->
                repository.getRoutes(startPoint, endPoint)
                    .setNetworkingThread()
                    .subscribe(
                        ::calculateMapData,
                        ::handleRemoteError
                    )
                    .track()
            }
        }
    }

    private fun setStartPoint(startPoint: Poi) {
        this.startPoint = startPoint
        _startPointName.onNext(startPoint.name)
    }

    private fun setEndPoint(endPoint: Poi) {
        this.endPoint = endPoint
        _endPointName.onNext(endPoint.name)
    }

    private fun calculateMapData(route: Route) {
        if (route.coordinates.isNotEmpty()) {

            _startPointMarkerPosition.onNext(route.coordinates.first())
            _endPointMarkerPosition.onNext(route.coordinates.last())

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