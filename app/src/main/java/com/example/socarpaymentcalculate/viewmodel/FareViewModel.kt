package com.example.socarpaymentcalculate.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.socarpaymentcalculate.data.enum.CarModel
import com.example.socarpaymentcalculate.data.enum.CarType
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.viewmodel.base.BaseViewModel


class FareViewModel : BaseViewModel() {

    private var route: Route? = null

    private val calculatedFare = MutableLiveData<String>()

    private val selectedCarType = MutableLiveData<CarType>()

    private val carModelList = MutableLiveData<List<CarModel>>()

    private val selectedCarModel = MutableLiveData<CarModel>()

    fun onSeletedCarType(selectedCarType: CarType) {
        this.selectedCarType.value = selectedCarType
        carModelList.value =
            CarModel.values()
                .filter {
                    it.type == selectedCarType
                }.toList()
    }

    fun onSelectedCarModel(selectedCarModel: CarModel) {
        this.selectedCarModel.value = selectedCarModel
        calculateFare()
    }

    fun setRoute(route: Route) {
        this.route = route
        calculateFare()
    }

    private fun calculateFare() {
        if (route != null && selectedCarModel.value != null) {
            calculatedFare.value =
                (route!!.totalDistance * selectedCarModel.value!!.farePerKiloMeter).toString()
        }
    }

}