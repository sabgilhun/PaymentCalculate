package com.example.socarpaymentcalculate

import androidx.room.TypeConverter
import com.example.socarpaymentcalculate.data.model.Poi
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converter {

    @TypeConverter
    fun fromPoiPair(poiPair: Pair<Poi, Poi>): String {
        return Gson().toJson(poiPair)
    }

    @TypeConverter
    fun jsonToPoiPair(json: String): Pair<Poi, Poi> {
        return Gson().fromJson(json, poiPairTypeToken)
    }

    @TypeConverter
    fun fromLatLngList(latLngList: List<LatLng>): String {
        return Gson().toJson(latLngList)
    }

    @TypeConverter
    fun jsonToLatLngList(json: String): List<LatLng> {
        return Gson().fromJson(json, latLngListTypeToken)
    }

    @TypeConverter
    fun fromPoiList(poiList: List<Poi>): String {
        return Gson().toJson(poiList)
    }

    @TypeConverter
    fun jsonToPoiList(json: String): List<Poi> {
        return Gson().fromJson(json, poiListTypeToken)
    }

    companion object {
        private val poiPairTypeToken: Type = object : TypeToken<Pair<Poi, Poi>>() {}.type

        private val latLngListTypeToken: Type = object : TypeToken<List<LatLng>>() {}.type

        private val poiListTypeToken: Type = object : TypeToken<List<Poi>>() {}.type
    }
}