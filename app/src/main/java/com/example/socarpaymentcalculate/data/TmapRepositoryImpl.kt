package com.example.socarpaymentcalculate.data

import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.data.remote.TmapDataSource
import com.example.socarpaymentcalculate.data.remote.TmapDataSourceImpl
import io.reactivex.Single

object TmapRepositoryImpl : TmapRepository {

    private val dataSource: TmapDataSource = TmapDataSourceImpl

    override fun getPois(
        keyword: String
    ): Single<List<Poi>> {
        return dataSource.getPois(keyword)
    }

    override fun getRoutes(
        startPoi: Poi,
        endPoi: Poi
    ): Single<Route> {
        return dataSource.getRoutes(startPoi, endPoi)
    }
}