package com.example.socarpaymentcalculate.data

import com.example.socarpaymentcalculate.common.Optional
import com.example.socarpaymentcalculate.data.datasource.TmapDataSource
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import io.reactivex.Single

class TmapRepositoryImpl private constructor(
    private val remoteDataSource: TmapDataSource,
    private val localDataSource: TmapDataSource
) : TmapRepository {

    override fun getPois(
        keyword: String
    ): Single<List<Poi>> {
        return Single.concat(
            localDataSource.getPois(keyword),
            remoteDataSource.getPois(keyword)
        ).filter(Optional<List<Poi>>::isNotEmpty)
            .map(Optional<List<Poi>>::get)
            .firstOrError()
    }

    override fun getRoutes(
        startPoi: Poi,
        endPoi: Poi
    ): Single<Route> {
        return Single.concat(
            localDataSource.getRoutes(startPoi, endPoi),
            remoteDataSource.getRoutes(startPoi, endPoi)
        ).filter(Optional<Route>::isNotEmpty)
            .map(Optional<Route>::get)
            .firstOrError()
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