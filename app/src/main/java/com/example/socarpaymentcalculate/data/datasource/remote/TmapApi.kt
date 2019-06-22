package com.example.socarpaymentcalculate.data.datasource.remote

import com.example.socarpaymentcalculate.data.datasource.remote.response.PoiSearchResponse
import com.example.socarpaymentcalculate.data.datasource.remote.response.RouteSearchResponse
import io.reactivex.Single
import retrofit2.http.*

interface TmapApi {

    @GET(value = "pois")
    fun getPois(
        @Query("version") version: String,
        @Query("searchKeyword") searchKeyword: String,
        @Query("appKey") appKey: String
    ): Single<PoiSearchResponse>

    @Headers(
        "appKey: 2d2cb58c-8552-4e62-9f91-eafa999cbfe1",
        "Content-Type: application/x-www-form-urlencoded"
    )
    @FormUrlEncoded
    @POST(value = "routes")
    fun getRoutes(
        @Query("version") version: String,
        @FieldMap values: HashMap<String, Any>
    ): Single<RouteSearchResponse>
}