package com.example.socarpaymentcalculate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.socarpaymentcalculate.data.TmapRepositoryImpl
import com.example.socarpaymentcalculate.viewmodel.fare.FareViewModel
import com.example.socarpaymentcalculate.viewmodel.map.MapViewModel
import com.example.socarpaymentcalculate.viewmodel.search.SearchViewModel

object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(FareViewModel::class.java) ->
                    FareViewModel(TmapRepositoryImpl)
                isAssignableFrom(MapViewModel::class.java) ->
                    MapViewModel(TmapRepositoryImpl)
                isAssignableFrom(SearchViewModel::class.java) ->
                    SearchViewModel(TmapRepositoryImpl)
                else ->
                    throw IllegalArgumentException("UnknownViewModelclass:${modelClass.name}")
            }
        } as T
}