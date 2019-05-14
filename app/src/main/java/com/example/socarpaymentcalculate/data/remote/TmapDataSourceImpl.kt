package com.example.socarpaymentcalculate.data.remote

import com.example.socarpaymentcalculate.data.model.Poi
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object TmapDataSourceImpl : TmapDataSource {

    private const val BASE_URL = "https://api2.sktelecom.com/tmap/"

    //TODO 앱 키 프로젝트 그레이들로 빼기
    private const val APP_KEY = "2d2cb58c-8552-4e62-9f91-eafa999cbfe1"

    private val retrofit: TmapApi

    init {
        retrofit = run {
            Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TmapApi::class.java)
        }
    }

    override fun getPois(searchKeyword: String): Single<List<Poi>> {
        return retrofit.getPois(
            version = "1",
            searchKeyword = searchKeyword,
            appKey = APP_KEY
        ).map { response ->
            response.searchPoiInfo.pois.poi.map {
                Poi.from(it)
            }.toList()
        }
    }

    override fun getRoutes(): Single<String> {

//        val values = mutableMapOf<String, Any>()

        val values = HashMap<String, Any>()
        values.put("startX", 127.034370)
        values.put("startY", 37.648343)
        values.put("endX", 127.025671)
        values.put("endY", 37.637652)
        values.put("totalValue", 1)

        return retrofit.getRoutes(
            version = "1",
            values = values
        ).map {
            it.features.get(0).properties.description
        }
    }

}