package com.example.socarpaymentcalculate.data.remote

import com.example.socarpaymentcalculate.data.model.Poi
import io.reactivex.Single

interface TmapDataSource {

    fun getPois(searchKeyword: String): Single<List<Poi>>

    fun getRoutes(): Single<String>

}