package com.example.socarpaymentcalculate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.socarpaymentcalculate.data.TmapRepository
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.viewmodel.base.BaseViewModel

class SearchViewModel(private val repository: TmapRepository) : BaseViewModel() {

    private val _searchedPois = MutableLiveData<List<Poi>>()
    val searchedPois: LiveData<List<Poi>>
        get() = _searchedPois

    val keyword = MutableLiveData<String>()

    fun searchPois() {
        val keyword = keyword.value

        if (!keyword.isNullOrBlank()) {
            compositeDisposable.add(
                repository
                    .getPois(keyword,
                        { _searchedPois.value = it },
                        {})
            )
        }
    }
}