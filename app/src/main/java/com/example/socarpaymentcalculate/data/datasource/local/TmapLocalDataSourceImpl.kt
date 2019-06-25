package com.example.socarpaymentcalculate.data.datasource.local

import com.example.socarpaymentcalculate.common.Optional
import com.example.socarpaymentcalculate.data.datasource.TmapDataSource
import com.example.socarpaymentcalculate.data.datasource.local.entity.PoisEntity
import com.example.socarpaymentcalculate.data.datasource.local.entity.RouteEntity
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import io.reactivex.Single

class TmapLocalDataSourceImpl private constructor(
    private val routeDao: RouteDao,
    private val searchedPoisDao: SearchedPoisDao
) : TmapDataSource {

    override fun getPois(keyword: String): Single<Optional<List<Poi>>> {
        return searchedPoisDao.getSearchedPoisByKeyword(keyword)
            .map(PoisEntity::pois)
            .map { Optional(it, null) }
            .onErrorReturn { Optional(null, it) }
    }

    override fun getRoutes(startPoi: Poi, endPoi: Poi): Single<Optional<Route>> {
        return routeDao.getRouteByPoiPair(Pair(startPoi, endPoi))
            .filter(::checkOldDataAndDelete)
            .map(Route.Companion::from)
            .map { Optional(it, null) }
            .toSingle()
            .onErrorReturn { Optional(null, it) }
    }

    override fun insertPois(keyword: String, searchedPois: List<Poi>) {
        searchedPoisDao.limitedInsertPois(PoisEntity.of(keyword, searchedPois))
    }

    override fun insertRoute(startPoi: Poi, endPoi: Poi, route: Route) {
        routeDao.limitedInsertRoute(RouteEntity.of(startPoi, endPoi, route))
    }

    private fun checkOldDataAndDelete(routeEntity: RouteEntity): Boolean {
        return if ((System.currentTimeMillis() - routeEntity.time) < 2 * HOUR_CONVERSION_TO_LONG) {
            true
        } else {
            routeDao.deleteRoute(routeEntity)
            false
        }
    }

    companion object {
        private const val HOUR_CONVERSION_TO_LONG = 3_600_000

        private var INSTANCE: TmapLocalDataSourceImpl? = null

        @JvmStatic
        fun getInstance(
            routeDao: RouteDao,
            searchedPoisDao: SearchedPoisDao
        ): TmapLocalDataSourceImpl {
            if (INSTANCE == null) {
                synchronized(TmapLocalDataSourceImpl::javaClass) {
                    INSTANCE = TmapLocalDataSourceImpl(routeDao, searchedPoisDao)
                }
            }
            return INSTANCE!!
        }
    }
}