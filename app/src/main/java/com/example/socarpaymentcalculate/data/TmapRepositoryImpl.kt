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
    ): Single<Optional<List<Poi>>> {
        return Single.concat(
            localDataSource.getPois(keyword),
            remoteDataSource.getPois(keyword)
                .doOnSuccess { localDataSource.insertPois(keyword, it.get()) }
        ).filter(Optional<List<Poi>>::isNotEmpty)
            .firstOrError()
            .onErrorReturn { Optional(null, it) }
    }

    override fun getRoutes(
        startPoi: Poi,
        endPoi: Poi
    ): Single<Optional<Route>> {
        return Single.concat(
            localDataSource.getRoutes(startPoi, endPoi),
            remoteDataSource.getRoutes(startPoi, endPoi)
                .doOnSuccess { localDataSource.insertRoute(startPoi, endPoi, it.get()) }
        ).filter(Optional<Route>::isNotEmpty)
            .firstOrError()
            .onErrorReturn { Optional(null, it) }
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