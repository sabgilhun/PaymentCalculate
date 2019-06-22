package com.example.socarpaymentcalculate.data

import com.example.socarpaymentcalculate.data.datasource.TmapDataSource
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.data.model.SearchedPois
import io.reactivex.Single

class TmapRepositoryImpl private constructor(
    private val remoteDataSource: TmapDataSource,
    private val localDataSource: TmapDataSource
) : TmapRepository {


    override fun getPois(
        keyword: String
    ): Single<SearchedPois> {
        return remoteDataSource.getPois(keyword)
    }

    override fun getRoutes(
        startPoi: Poi,
        endPoi: Poi
    ): Single<Route> {
        return remoteDataSource.getRoutes(startPoi, endPoi)
    }

    companion object {
        private var INSTANCE: TmapRepository? = null

        @JvmStatic
        fun getInstance(
            remoteDataSource: TmapDataSource,
            localDataSource: TmapDataSource
        ): TmapRepository {
            if (INSTANCE == null) {
                synchronized(TmapRepository::javaClass) {
                    INSTANCE = TmapRepositoryImpl(remoteDataSource, localDataSource)
                }
            }
            return INSTANCE!!
        }
    }
}