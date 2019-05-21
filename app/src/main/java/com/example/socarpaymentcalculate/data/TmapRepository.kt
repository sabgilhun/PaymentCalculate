package com.example.socarpaymentcalculate.data

import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import io.reactivex.disposables.Disposable

interface TmapRepository {

    fun getPois(
        keyword: String,
        success: (List<Poi>) -> Unit,
        fail: (String) -> Unit
    ): Disposable

    fun getRoutes(
        startPoi: Poi,
        endPoi: Poi,
        success: (Route) -> Unit,
        fail: (String) -> Unit
    ): Disposable

}