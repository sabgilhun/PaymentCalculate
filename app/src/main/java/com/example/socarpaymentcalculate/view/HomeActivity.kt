package com.example.socarpaymentcalculate.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.socarpaymentcalculate.Constants.EXTRA_POI
import com.example.socarpaymentcalculate.Constants.EXTRA_VIEW_ID
import com.example.socarpaymentcalculate.Constants.REQUEST_CODE_HOME_TO_SEARCH
import com.example.socarpaymentcalculate.R
import com.example.socarpaymentcalculate.adapter.CarModelAdapter
import com.example.socarpaymentcalculate.adapter.CarTypeAdapter
import com.example.socarpaymentcalculate.data.enums.CarType
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.data.model.Route
import com.example.socarpaymentcalculate.databinding.ActivityHomeBinding
import com.example.socarpaymentcalculate.view.base.BaseActivity
import com.example.socarpaymentcalculate.viewmodel.map.MapViewModel
import com.example.socarpaymentcalculate.viewmodel.fare.DetermineRouteAction
import com.example.socarpaymentcalculate.viewmodel.fare.FareViewModel
import com.example.socarpaymentcalculate.viewmodel.fare.SelectCarModelAction
import com.example.socarpaymentcalculate.viewmodel.fare.SelectCarTypeAction

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private lateinit var fareViewModel: FareViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.mapViewModel = getViewModel(MapViewModel::class.java)

        fareViewModel = getViewModel(FareViewModel::class.java)

        LinearLayoutManager(applicationContext).also {
            it.orientation = LinearLayoutManager.HORIZONTAL
            binding.rvCarType.layoutManager = it
            binding.rvCarType.addItemDecoration(DividerItemDecoration(applicationContext, it.orientation))
        }

        binding.rvCarType.adapter = CarTypeAdapter(applicationContext) {
            fareViewModel.actionStream.onNext(SelectCarTypeAction(it))
        }
        binding.carTypeList = CarType.values().toList()

        LinearLayoutManager(applicationContext).also {
            it.orientation = LinearLayoutManager.HORIZONTAL
            binding.rvCarModel.layoutManager = it
            binding.rvCarModel.addItemDecoration(DividerItemDecoration(applicationContext, it.orientation))
        }

        binding.rvCarModel.adapter = CarModelAdapter(applicationContext) {
            fareViewModel.actionStream.onNext(SelectCarModelAction(it))
        }

        binding.mapViewModel?.route?.observe(this, Observer<Route> {
            fareViewModel.actionStream.onNext(DetermineRouteAction(it))
        })


        fareViewModel.carModelList.observe {
            binding.carModelList = it
        }

        fareViewModel.calculatedFare.observe {
            binding.tvFare.text = it
        }
    }

    fun onClickSearchTextBox(view: View) {
        val intent = when {
            view.id == R.id.tv_departure ->
                Intent(this, SearchActivity::class.java)
                    .putExtra(EXTRA_VIEW_ID, R.id.tv_departure)

            view.id == R.id.tv_destination ->
                Intent(this, SearchActivity::class.java)
                    .putExtra(EXTRA_VIEW_ID, R.id.tv_destination)

            else ->
                throw IllegalStateException("Unknown view $view")
        }

        startActivityForResult(intent, REQUEST_CODE_HOME_TO_SEARCH)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) {
            return
        }

        if (requestCode == REQUEST_CODE_HOME_TO_SEARCH) {
            with(data?.getIntExtra(EXTRA_VIEW_ID, 0)) {
                when {
                    this == R.id.tv_departure ->
                        (data?.getParcelableExtra(EXTRA_POI) as? Poi)
                            ?.also {
                                binding.mapViewModel?.setDeparture(it)
                            }

                    this == R.id.tv_destination ->
                        (data?.getParcelableExtra(EXTRA_POI) as? Poi)
                            ?.also {
                                binding.mapViewModel?.setDestination(it)
                            }

                    else ->
                        throw IllegalStateException("Unknown view id $this")
                }
            }
        }
    }

}
