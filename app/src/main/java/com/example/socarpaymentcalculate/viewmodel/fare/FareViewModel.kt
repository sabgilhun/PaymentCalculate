package com.example.socarpaymentcalculate.viewmodel.fare

import com.example.socarpaymentcalculate.common.filterTo
import com.example.socarpaymentcalculate.data.TmapRepository
import com.example.socarpaymentcalculate.data.enums.CarModel
import com.example.socarpaymentcalculate.data.enums.CarType
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.viewmodel.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject


class FareViewModel(private val repository: TmapRepository) : BaseViewModel() {

    private val route = BehaviorSubject.create<Route>()

    private val _selectedCarModel = BehaviorSubject.create<CarModel>()
    val selectedCarModel: Observable<CarModel>
        get() = _selectedCarModel

    private val _selectedCarType = BehaviorSubject.create<CarType>()
    val selectedCarType: Observable<CarType>
        get() = _selectedCarType

    val carModelsOfSelectedCarType: Observable<List<CarModel>>
        get() = _selectedCarType.map {
            CarModel.values().filter { carModel ->
                carModel.type == it
            }.toList()
        }

    private val _calculatedFare = PublishSubject.create<Int>()
    val calculatedFare: Observable<String>
        get() = _calculatedFare.map(Int::toString)

    init {
        actionStream
            .filterTo(SelectCarTypeAction::class.java)
            .map { it.carType }
            .subscribe(::onSelectCarType)
            .track()

        actionStream
            .filterTo(SelectCarModelAction::class.java)
            .map { it.carModel }
            .subscribe(::onSelectCarModel)
            .track()

        actionStream
            .filterTo(DetermineRouteAction::class.java)
            .map { it.route }
            .subscribe(::setRoute)
            .track()

        calculateFare()
    }

    private fun onSelectCarType(selectedCarType: CarType) {
        _selectedCarType.onNext(selectedCarType)
    }

    private fun onSelectCarModel(selectedCarModel: CarModel) {
        _selectedCarModel.onNext(selectedCarModel)
    }

    private fun setRoute(route: Route) {
        this.route.onNext(route)
    }

    private fun calculateFare() {
        Observables.combineLatest(
            route,
            _selectedCarModel
        ) { route, carModel ->
            (route.totalDistance * carModel.farePerKiloMeter)
        }.subscribe(_calculatedFare::onNext)
            .track()
    }

}