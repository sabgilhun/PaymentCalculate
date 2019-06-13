package com.example.socarpaymentcalculate.viewmodel.map

import com.example.socarpaymentcalculate.common.filterTo
import com.example.socarpaymentcalculate.data.TmapRepository
import com.example.socarpaymentcalculate.data.model.LatLngFactory
import com.example.socarpaymentcalculate.data.model.Navigation
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.viewmodel.base.BaseViewModel
import com.google.android.gms.maps.model.LatLngBounds
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class MapViewModel(private val repository: TmapRepository) : BaseViewModel() {

    private val _navigation = BehaviorSubject.create<Navigation>()
    val navigation: Observable<Navigation>
        get() = _navigation

    private val _searchedStartPoint = BehaviorSubject.create<Poi>()
    val searchedStartPoint: Observable<String>
        get() = _searchedStartPoint.map(Poi::name)

    private val _searchedEndPoint = BehaviorSubject.create<Poi>()
    val searchedEndPoint: Observable<String>
        get() = _searchedEndPoint.map(Poi::name)

    private var searchSubscription: Disposable? = null

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

        onClickSearch()
    }

    private fun onClickSearch() {

        if (searchSubscription != null) {
            searchSubscription?.dispose()
            searchSubscription = null
        }

        searchSubscription =
            Observables.combineLatest(
                _searchedStartPoint,
                _searchedEndPoint
            ) { start, end ->
                Pair(start, end)
            }.observeOn(Schedulers.io())
                .flatMap {
                    repository.getRoutes(it.first, it.second)
                        .toObservable()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    ::calculateMapData,
                    ::handleRemoteError
                )
    }

    private fun setStartPoint(startPoint: Poi) {
        _searchedStartPoint.onNext(startPoint)
    }

    private fun setEndPoint(endPoint: Poi) {
        _searchedEndPoint.onNext(endPoint)
    }

    private fun calculateMapData(route: Route) {
        if (route.coordinates.isNotEmpty()) {

            val navigation = Navigation(
                route.coordinates.first(),
                route.coordinates.last(),
                LatLngBounds(
                    LatLngFactory.leftTop(route.coordinates),
                    LatLngFactory.rightBottom(route.coordinates)
                ),
                route
            )

            _navigation.onNext(navigation)
        }
    }
}