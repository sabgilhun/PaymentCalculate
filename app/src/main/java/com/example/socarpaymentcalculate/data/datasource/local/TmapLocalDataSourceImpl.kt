package com.example.socarpaymentcalculate.data.datasource.local

import com.example.socarpaymentcalculate.common.Optional
import com.example.socarpaymentcalculate.data.datasource.TmapDataSource
import com.example.socarpaymentcalculate.data.datasource.local.entity.PoisEntity
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
            .map { Optional(it) }
            .onErrorReturn { Optional(null) }
    }

    override fun getRoutes(startPoi: Poi, endPoi: Poi): Single<Optional<Route>> {
        return routeDao.getRouteByPoiPair(Pair(startPoi, endPoi))
            .map(Route.Companion::from)
            .map { Optional(it) }
            .onErrorReturn { Optional(null) }
    }

    override fun insertPois(searchedPois: List<Poi>) {

    }

    companion object {
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