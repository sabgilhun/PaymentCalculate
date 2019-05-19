package com.example.socarpaymentcalculate.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.socarpaymentcalculate.data.enum.CarModel
import com.example.socarpaymentcalculate.data.enum.CarType
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.viewmodel.base.BaseViewModel


class FareViewModel : BaseViewModel() {

    private val fare = MutableLiveData<String>()

    private val searchedRoute = MutableLiveData<Route>()

    private val selectedCarType = MutableLiveData<CarType>()

    private val selectedCarModel = MutableLiveData<CarModel>()

    private val carModelList = MutableLiveData<List<CarModel>>()

    fun setSeletedCarType(selectedCarType: CarType) {
        this.selectedCarType.value = selectedCarType
        carModelList.value =
            CarModel.values()
                .filter {
                    it.type == selectedCarType
                }.toList()
    }

    fun setSelectedCarModel(selectedCarModel: CarModel) {
        this.selectedCarModel.value = selectedCarModel
        calculateFare()
    }

    fun setSearchedRoute(searchedRoute: Route) {
        this.searchedRoute.value = searchedRoute
        calculateFare()
    }

    private fun calculateFare() {

        if (searchedRoute.value != null && selectedCarModel.value != null) {
            fare.value =
                (searchedRoute.value!!.totalDistance * selectedCarModel.value!!.farePerKiloMeter).toString()
        }
    }
}