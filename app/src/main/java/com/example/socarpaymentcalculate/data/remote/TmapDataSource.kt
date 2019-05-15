package com.example.socarpaymentcalculate.data.remote

import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import io.reactivex.Maybe
import io.reactivex.Single

interface TmapDataSource {

    fun getPois(searchKeyword: String): Single<List<Poi>>

    fun getRoutes(startPoi: Poi, endPoi: Poi): Single<Route>

}