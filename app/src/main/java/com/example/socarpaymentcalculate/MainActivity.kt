package com.example.socarpaymentcalculate

import android.os.Bundle
import android.util.Log
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.remote.TmapDataSourceImpl
import com.example.socarpaymentcalculate.databinding.ActivityMainBinding
import com.example.socarpaymentcalculate.view.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var compositeDisposable = CompositeDisposable()

        var poi: Poi? = null
        var poi2: Poi? = null

        binding.poi.setOnClickListener {
            compositeDisposable.add(
                TmapDataSourceImpl
                    .getPois("공덕역")
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            poi = it[0]
                            poi2 = it[1]

                        },
                        {
                            Log.d("테스트", "실패")
                        })
            )
        }

        binding.route.setOnClickListener {
            compositeDisposable.add(
                TmapDataSourceImpl
                    .getRoutes(poi!!, poi2!!)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            Log.d("테스트", it.toString())
                        },
                        {
                            it.printStackTrace()
                            Log.d("테스트", "실패")
                        })
            )
        }

    }

}
