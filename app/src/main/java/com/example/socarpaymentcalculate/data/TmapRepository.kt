package com.example.socarpaymentcalculate.data

import com.example.socarpaymentcalculate.common.Optional
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import io.reactivex.Single

interface TmapRepository {

    fun getPois(
        keyword: String
    ): Single<Optional<List<Poi>>>

    fun getRoutes(
        startPoi: Poi,
        endPoi: Poi
    ): Single<Optional<Route>>

}