package com.example.socarpaymentcalculate.common

import android.content.Context
import com.example.socarpaymentcalculate.data.TmapRepository
import com.example.socarpaymentcalculate.data.TmapRepositoryImpl
import com.example.socarpaymentcalculate.data.datasource.local.LocalDatabase
import com.example.socarpaymentcalculate.data.datasource.local.TmapLocalDataSourceImpl
import com.example.socarpaymentcalculate.data.datasource.remote.TmapRemoteDataSourceImpl

object Injection {

    fun provideTmapRepository(context: Context): TmapRepository {
        return TmapRepositoryImpl.getInstance(
            remoteDataSource = TmapRemoteDataSourceImpl.getInstance(),
            localDataSource = TmapLocalDataSourceImpl.getInstance(
                LocalDatabase.getInstance(context).routeDao(), LocalDatabase.getInstance(context).searchedPois()
            )
        )
    }
}