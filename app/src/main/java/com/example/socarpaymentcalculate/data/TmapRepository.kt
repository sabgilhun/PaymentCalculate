package com.example.socarpaymentcalculate.data

import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import io.reactivex.Single

interface TmapRepository {

    fun getPois(
        keyword: String
    ): Single<List<Poi>>

    fun getRoutes(
        startPoi: Poi,
        endPoi: Poi
    ): Single<Route>

}