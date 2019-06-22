package com.example.socarpaymentcalculate.viewmodel.fare

import com.example.socarpaymentcalculate.common.filterTo
import com.example.socarpaymentcalculate.data.enums.CarModel
import com.example.socarpaymentcalculate.data.enums.CarType
import com.example.socarpaymentcalculate.data.model.Fare
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.viewmodel.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject


class FareViewModel : BaseViewModel() {

    private val route = BehaviorSubject.create<Route>()

    private val _selectedCarType = BehaviorSubject.createDefault(CarType.SUBCOMPACT)
    val selectedCarType: Observable<CarType>
        get() = _selectedCarType

    private val _calculatedFare = PublishSubject.create<List<Fare>>()
    val calculatedFare: Observable<List<Fare>>
        get() = _calculatedFare

    init {
        actionStream
            .filterTo(SelectCarTypeAction::class.java)
            .map { it.carType }
            .subscribe(::onSelectCarType)
            .track()

        actionStream
            .filterTo(DetermineRouteAction::class.java)
            .map { it.route }
            .subscribe(::setRoute)
            .track()

        subscribeRouteDataToCalculateFare()
    }

    private fun onSelectCarType(selectedCarType: CarType) {
        _selectedCarType.onNext(selectedCarType)
    }

    private fun setRoute(route: Route) {
        this.route.onNext(route)
    }

    private fun subscribeRouteDataToCalculateFare() {
        Observables.combineLatest(
            route,
            _selectedCarType
        ) { route, carType ->
            CarModel.values()
                .filter { it.type == carType }
                .map { carModel ->
                    val totalMinutePerTen: Int = (route.totalTime / 60) / 10
                    val drivingFare: Int = route.totalDistance * carModel.farePerKiloMeter

                    Fare.of(
                        carModel,
                        route,
                        (drivingFare + totalMinutePerTen * carModel.weekdayFarePerTenMinute),
                        (drivingFare + totalMinutePerTen * carModel.weekendFarePerTenMinute)
                    )
                }
        }.subscribe(_calculatedFare::onNext)
            .track()
    }
}