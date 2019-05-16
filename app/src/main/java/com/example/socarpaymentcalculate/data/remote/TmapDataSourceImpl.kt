package com.example.socarpaymentcalculate.data.remote

import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.data.remote.reqeust.RouteSearchRequestBody
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object TmapDataSourceImpl : TmapDataSource {

    private const val BASE_URL = "https://api2.sktelecom.com/tmap/"

    private const val TMAP_VERSION = "1"

    //TODO 앱 키 프로젝트 그레이들로 빼기
    private const val APP_KEY = "2d2cb58c-8552-4e62-9f91-eafa999cbfe1"

    private val retrofit: TmapApi

    init {
        retrofit = run {
            Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .build()
                )
                .build()
                .create(TmapApi::class.java)
        }
    }

    override fun getPois(searchKeyword: String): Single<List<Poi>> {

        return retrofit.getPois(
            version = TMAP_VERSION,
            searchKeyword = searchKeyword,
            appKey = APP_KEY
        ).map { response ->
            response.searchPoiInfo.pois.poi.map {
                Poi.from(it)
            }.toList()
        }
    }

    override fun getRoutes(startPoi: Poi, endPoi: Poi): Single<Route> {

        return retrofit.getRoutes(
            version = TMAP_VERSION,
            values = RouteSearchRequestBody.of(startPoi, endPoi, true).fieldMap
        ).map {
            Route.from(it)
        }
    }

}