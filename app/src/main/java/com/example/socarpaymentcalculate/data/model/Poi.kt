package com.example.socarpaymentcalculate.data.model

import android.os.Parcel
import android.os.Parcelable
import com.example.socarpaymentcalculate.data.remote.response.PoiSearchResponse

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
        fun from(poiSearchResponse: PoiSearchResponse.SearchPoiInfo.Pois.Poi): Poi {

            //TODO: 글자 없는 경우 예외 처리 해야 깔끔하게 나옴
            val oldAddressName = poiSearchResponse.upperAddrName + " " +
                    poiSearchResponse.middleAddrName + " " +
                    poiSearchResponse.lowerAddrName + " " +
                    poiSearchResponse.firstNo + "-" +
                    poiSearchResponse.secondNo

            val newAddressName = poiSearchResponse.upperAddrName + " " +
                    poiSearchResponse.middleAddrName + " " +
                    poiSearchResponse.roadName + " " +
                    poiSearchResponse.firstBuildNo + "-" +
                    poiSearchResponse.secondBuildNo

            return Poi(
                poiSearchResponse.id,
                poiSearchResponse.name,
                poiSearchResponse.noorLat,
                poiSearchResponse.noorLon,
                oldAddressName,
                newAddressName
            )
        }

        @JvmField
        val CREATOR: Parcelable.Creator<Poi> = object : Parcelable.Creator<Poi> {
            override fun createFromParcel(source: Parcel): Poi = Poi(source)
            override fun newArray(size: Int): Array<Poi?> = arrayOfNulls(size)
        }
    }
}