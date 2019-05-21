package com.example.socarpaymentcalculate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.socarpaymentcalculate.data.TmapRepositoryImpl

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
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

}