package com.example.socarpaymentcalculate.data

import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.data.remote.TmapDataSource
import com.example.socarpaymentcalculate.data.remote.TmapDataSourceImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

object TmapRepositoryImpl : TmapRepository {

    private val dataSource: TmapDataSource = TmapDataSourceImpl

    override fun getPois(
        keyword: String,
        success: (List<Poi>) -> Unit,
        fail: (String) -> Unit
    ): Disposable {
        return dataSource
            .getPois(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(success, {
                fail.invoke("서버에서 데이터를 가져오는데에 실패하였습니다.")
            })
    }

    override fun getRoutes(
        startPoi: Poi,
        endPoi: Poi,
        success: (Route) -> Unit,
        fail: (String) -> Unit
    ): Disposable {
        return dataSource
            .getRoutes(startPoi, endPoi)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(success, {
                fail.invoke("서버에서 데이터를 가져오는데에 실패하였습니다.")
            })
    }

}