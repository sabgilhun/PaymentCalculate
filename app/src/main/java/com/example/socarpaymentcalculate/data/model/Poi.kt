package com.example.socarpaymentcalculate.data.model

import android.os.Parcel
import android.os.Parcelable
import com.example.socarpaymentcalculate.data.datasource.remote.response.PoiSearchResponse

data class Poi private constructor(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val oldAddressName: String,
    val newAddressName: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString() ?: "",
        source.readString() ?: "",
        source.readDouble(),
        source.readDouble(),
        source.readString() ?: "",
        source.readString() ?: ""
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(name)
        writeDouble(latitude)
        writeDouble(longitude)
        writeString(oldAddressName)
        writeString(newAddressName)
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<Poi> = object : Parcelable.Creator<Poi> {
            override fun createFromParcel(source: Parcel): Poi = Poi(source)
            override fun newArray(size: Int): Array<Poi?> = arrayOfNulls(size)
        }

        fun from(poiSearchResponse: PoiSearchResponse.SearchPoiInfo.Pois.Poi): Poi {

            val oldAddressName = StringBuilder(poiSearchResponse.upperAddrName).apply {
                if (poiSearchResponse.middleAddrName.isNotEmpty()) {
                    append(" " + poiSearchResponse.middleAddrName)
                }
                if (poiSearchResponse.lowerAddrName.isNotEmpty()) {
                    append(" " + poiSearchResponse.lowerAddrName)
                }
                if (poiSearchResponse.firstNo.isNotEmpty()) {
                    append(" " + poiSearchResponse.firstNo)
                }
                if (poiSearchResponse.secondNo.isNotEmpty() && poiSearchResponse.secondNo != "0") {
                    append("-" + poiSearchResponse.secondNo)
                }
            }.toString()

            val newAddressName = StringBuilder(poiSearchResponse.upperAddrName).apply {
                if (poiSearchResponse.middleAddrName.isNotEmpty()) {
                    append(" " + poiSearchResponse.middleAddrName)
                }
                if (poiSearchResponse.roadName.isNotEmpty()) {
                    append(" " + poiSearchResponse.roadName)
                }
                if (poiSearchResponse.firstBuildNo.isNotEmpty()) {
                    append(" " + poiSearchResponse.firstBuildNo)
                }
                if (poiSearchResponse.secondBuildNo.isNotEmpty() && poiSearchResponse.secondBuildNo != "0") {
                    append("-" + poiSearchResponse.secondBuildNo)
                }
            }.toString()

            return Poi(
                poiSearchResponse.id,
                poiSearchResponse.name,
                poiSearchResponse.noorLat,
                poiSearchResponse.noorLon,
                oldAddressName,
                newAddressName
            )
        }
    }
}