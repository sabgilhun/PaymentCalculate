package com.example.socarpaymentcalculate.data.datasource.local

import com.example.socarpaymentcalculate.data.datasource.TmapDataSource
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.data.model.SearchedPois
import io.reactivex.Single

class TmapLocalDataSourceImpl private constructor(
    private val routeDao: RouteDao,
    private val searchedPoisDao: SearchedPoisDao
) : TmapDataSource {


    override fun getPois(keyword: String): Single<SearchedPois> {
        return searchedPoisDao.getSearchedPoisByKeyword(keyword)
    }

    override fun getRoutes(startPoi: Poi, endPoi: Poi): Single<Route> {
        return routeDao.getRouteByPoiPair(Pair(startPoi, endPoi))
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