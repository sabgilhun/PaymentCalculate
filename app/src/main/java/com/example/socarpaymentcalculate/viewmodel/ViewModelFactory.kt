package com.example.socarpaymentcalculate.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.socarpaymentcalculate.common.Injection
import com.example.socarpaymentcalculate.data.TmapRepository
import com.example.socarpaymentcalculate.viewmodel.fare.FareViewModel
import com.example.socarpaymentcalculate.viewmodel.map.MapViewModel
import com.example.socarpaymentcalculate.viewmodel.search.SearchViewModel

class ViewModelFactory(
    private val tmapRepository: TmapRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(FareViewModel::class.java) ->
                    FareViewModel()
                isAssignableFrom(MapViewModel::class.java) ->
                    MapViewModel(tmapRepository)
                isAssignableFrom(SearchViewModel::class.java) ->
                    SearchViewModel(tmapRepository)
                else ->
                    throw IllegalArgumentException("UnknownViewModelclass:${modelClass.name}")
            }
        } as T

    companion object {

        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(
                    Injection.provideTmapRepository(application.applicationContext)
                )
                    .also { INSTANCE = it }
            }
    }
}