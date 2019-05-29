package com.example.socarpaymentcalculate.viewmodel.fare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.socarpaymentcalculate.common.filterTo
import com.example.socarpaymentcalculate.data.TmapRepository
import com.example.socarpaymentcalculate.data.enums.CarModel
import com.example.socarpaymentcalculate.data.enums.CarType
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.viewmodel.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class FareViewModel(private val repository: TmapRepository) : BaseViewModel() {

    private var route: Route? = null
    private var carModel: CarModel? = null

    private var _selectedCarType = PublishSubject.create<CarType>()
    val selectedCarType: Observable<CarType>
        get() = _selectedCarType

    private var _selectedCarModel = PublishSubject.create<CarModel>()
    val selectedCarModel: Observable<CarModel>
        get() = _selectedCarModel

    private val _carModelList = PublishSubject.create<List<CarModel>>()
    val carModelList: Observable<List<CarModel>>
        get() = _carModelList

    private val _calculatedFare = PublishSubject.create<String>()
    val calculatedFare: Observable<String>
        get() = _calculatedFare

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
    }

    private fun onSelectCarType(selectedCarType: CarType) {
        _selectedCarType.onNext(selectedCarType)

        _carModelList.onNext(CarModel.values()
            .filter { it.type == selectedCarType }
            .toList()
        )
    }

    private fun onSelectCarModel(selectedCarModel: CarModel) {
        _selectedCarModel.onNext(selectedCarModel)
        this.carModel = selectedCarModel

        calculateFare()
    }

    private fun setRoute(route: Route) {
        this.route = route

        calculateFare()
    }

    private fun calculateFare() {
        if (route != null && carModel != null) {
            _calculatedFare.onNext(
                ((route!!.totalDistance / 1000.0) * carModel!!.farePerKiloMeter).toInt().toString()
            )
        }
    }

}