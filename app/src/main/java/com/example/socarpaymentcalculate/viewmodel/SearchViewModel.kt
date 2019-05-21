package com.example.socarpaymentcalculate.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.socarpaymentcalculate.data.TmapRepository
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.viewmodel.base.BaseViewModel

class SearchViewModel(private val repository: TmapRepository) : BaseViewModel() {

    private val searchedPois = MutableLiveData<List<Poi>>()

    fun searchPois(keyword: String) {
        compositeDisposable.add(
            repository
                .getPois(keyword,
                    { searchedPois.value = it },
                    {})
        )
    }
}