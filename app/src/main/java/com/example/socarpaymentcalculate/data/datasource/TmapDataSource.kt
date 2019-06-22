package com.example.socarpaymentcalculate.data.datasource

import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.data.model.SearchedPois
import io.reactivex.Single

interface TmapDataSource {

    fun getPois(
        keyword: String
    ): Single<SearchedPois>

    fun getRoutes(
        startPoi: Poi,
        endPoi: Poi
    ): Single<Route>

}