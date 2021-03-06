package com.example.socarpaymentcalculate.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.socarpaymentcalculate.data.datasource.local.entity.PoisEntity
import com.example.socarpaymentcalculate.data.datasource.local.entity.RouteEntity

@Database(
    entities = [RouteEntity::class, PoisEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun routeDao(): RouteDao

    abstract fun searchedPois(): SearchedPoisDao

    companion object {

        private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase {
            synchronized(LocalDatabase::class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDatabase::class.java,
                        "socar_payment_calculator.db"
                    ).build()
                }
                return INSTANCE!!
            }
        }
    }
}