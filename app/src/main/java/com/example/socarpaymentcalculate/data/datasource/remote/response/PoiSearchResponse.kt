package com.example.socarpaymentcalculate.data.datasource.remote.response


import com.google.gson.annotations.SerializedName

data class PoiSearchResponse(
    @SerializedName("searchPoiInfo")
    val searchPoiInfo: SearchPoiInfo
) {
    data class SearchPoiInfo(
        @SerializedName("totalCount")
        val totalCount: String,
        @SerializedName("count")
        val count: String,
        @SerializedName("page")
        val page: String,
        @SerializedName("pois")
        val pois: Pois
    ) {
        data class Pois(
            @SerializedName("poi")
            val poi: List<Poi>
        ) {
            data class Poi(
                @SerializedName("id")
                val id: String,

                @SerializedName("name")
                val name: String,

                @SerializedName("telNo")
                val telNo: String,

                @SerializedName("frontLat")
                val frontLat: String,

                @SerializedName("frontLon")
                val frontLon: String,

                @SerializedName("noorLat")
                val noorLat: Double,

                @SerializedName("noorLon")
                val noorLon: Double,

                @SerializedName("upperAddrName")
                val upperAddrName: String,

                @SerializedName("middleAddrName")
                val middleAddrName: String,

                @SerializedName("lowerAddrName")
                val lowerAddrName: String,

                @SerializedName("detailAddrName")
                val detailAddrName: String,

                @SerializedName("mlClass")
                val mlClass: String,

                @SerializedName("firstNo")
                val firstNo: String,

                @SerializedName("secondNo")
                val secondNo: String,

                @SerializedName("roadName")
                val roadName: String,

                @SerializedName("firstBuildNo")
                val firstBuildNo: String,

                @SerializedName("secondBuildNo")
                val secondBuildNo: String,

                @SerializedName("radius")
                val radius: String,

                @SerializedName("bizName")
                val bizName: String,

                @SerializedName("upperBizName")
                val upperBizName: String,

                @SerializedName("middleBizName")
                val middleBizName: String,

                @SerializedName("lowerBizName")
                val lowerBizName: String,

                @SerializedName("detailBizName")
                val detailBizName: String,

                @SerializedName("rpFlag")
                val rpFlag: String,

                @SerializedName("parkFlag")
                val parkFlag: String,

                @SerializedName("detailInfoFlag")
                val detailInfoFlag: String,

                @SerializedName("desc")
                val desc: String
            )
        }
    }
}