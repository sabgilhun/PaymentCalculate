package com.example.socarpaymentcalculate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.socarpaymentcalculate.data.TmapRepository
import com.example.socarpaymentcalculate.data.enums.CarModel
import com.example.socarpaymentcalculate.data.enums.CarType
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.viewmodel.base.BaseViewModel


class FareViewModel(private val repository: TmapRepository) : BaseViewModel() {

    private var route: Route? = null

    private val _calculatedFare = MutableLiveData<String>()
    val calculatedFare: LiveData<String>
        get() = _calculatedFare

    private val _selectedCarType = MutableLiveData<CarType>()
    val selectedCarType: LiveData<CarType>
        get() = _selectedCarType

    private val _carModelList = MutableLiveData<List<CarModel>>()
    val carModelList: LiveData<List<CarModel>>
        get() = _carModelList

    private val _selectedCarModel = MutableLiveData<CarModel>()
    val selectedCarModel: LiveData<CarModel>
        get() = _selectedCarModel

    fun onSeletedCarType(selectedCarType: CarType) {
        _selectedCarType.value = selectedCarType
        _carModelList.value =
            CarModel.values()
                .filter {
                    it.type == selectedCarType
                }.toList()
    }

    fun onSelectedCarModel(selectedCarModel: CarModel) {
        _selectedCarModel.value = selectedCarModel
        calculateFare()
    }

    fun setRoute(route: Route) {
        this.route = route
        calculateFare()
    }

    private fun calculateFare() {
        if (route != null && _selectedCarModel.value != null) {
            _calculatedFare.value =
                (route!!.totalDistance * _selectedCarModel.value!!.farePerKiloMeter).toString()
        }
    }

}