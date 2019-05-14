package com.example.socarpaymentcalculate.data.model

import com.example.socarpaymentcalculate.data.remote.response.PoiSearchResponse

data class Poi private constructor(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val oldAddressName: String,
    val newAddressName: String
) {
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
                poiSearchResponse.noorLat.toDouble(),
                poiSearchResponse.noorLon.toDouble(),
                oldAddressName,
                newAddressName
            )
        }

    }
}