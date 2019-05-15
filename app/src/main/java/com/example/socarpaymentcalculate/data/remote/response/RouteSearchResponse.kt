package com.example.socarpaymentcalculate.data.remote.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RouteSearchResponse(
    @SerializedName("type")
    val type: String,

    @SerializedName("features")
    val features: List<Feature>
) {

    data class Feature(

        @SerializedName("type")
        val type: String,

        @SerializedName("geometry")
        val geometry: Geometry,

        @SerializedName("properties")
        val properties: Properties

    ) {

        data class Geometry(
            @SerializedName("type")
            val type: String,

            @SerializedName("coordinates")
            val coordinates: List<Any>

        )

        data class Properties(
            @SerializedName("totalDistance")
            @Expose
            val totalDistance: Int?,

            @SerializedName("totalTime")
            @Expose
            val totalTime: Int?,

            @SerializedName("totalFare")
            @Expose
            val totalFare: Int?,

            @SerializedName("taxiFare")
            @Expose
            val taxiFare: Int?,

            @SerializedName("index")
            @Expose
            val index: Int?,

            @SerializedName("pointIndex")
            @Expose
            val pointIndex: Int?,

            @SerializedName("lineIndex")
            @Expose
            val lineIndex: Int?,

            @SerializedName("name")
            @Expose
            val name: String?,

            @SerializedName("description")
            @Expose
            val description: String?,

            @SerializedName("distance")
            @Expose
            val distance: Int?,

            @SerializedName("time")
            @Expose
            val time: Int?,

            @SerializedName("nextRoadName")
            @Expose
            val nextRoadName: String?,

            @SerializedName("turnType")
            @Expose
            val turnType: Int?,

            @SerializedName("pointType")
            @Expose
            val pointType: String?,

            @SerializedName("roadType")
            @Expose
            val roadType: Int?,

            @SerializedName("facilityType")
            @Expose
            val facilityType: Int?
        )
    }

}