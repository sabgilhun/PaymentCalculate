package com.example.socarpaymentcalculate

import android.os.Bundle
import android.util.Log
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

//        compositeDisposable.add(
//            TmapDataSourceImpl
//                .getPois("공덕역")
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    {
//                        Log.d("테스트", it.toString())
//                    },
//                    {
//                        Log.d("테스트", "실패")
//                    })
//        )

        compositeDisposable.add(
            TmapDataSourceImpl
                .getRoutes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d("테스트", it)
                    },
                    {
                        Log.d("테스트", "실패")
                    })
        )
    }

}
