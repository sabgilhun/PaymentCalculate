package com.example.socarpaymentcalculate.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.socarpaymentcalculate.Constants.EXTRA_POI
import com.example.socarpaymentcalculate.Constants.EXTRA_VIEW_ID
import com.example.socarpaymentcalculate.Constants.REQUEST_CODE_HOME_TO_SEARCH
import com.example.socarpaymentcalculate.R
import com.example.socarpaymentcalculate.adapter.CarModelAdapter
import com.example.socarpaymentcalculate.adapter.CarTypeAdapter
import com.example.socarpaymentcalculate.common.setClickListener
import com.example.socarpaymentcalculate.common.setItem
import com.example.socarpaymentcalculate.data.enums.CarModel
import com.example.socarpaymentcalculate.data.enums.CarType
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.databinding.ActivityHomeBinding
import com.example.socarpaymentcalculate.view.base.BaseActivity
import com.example.socarpaymentcalculate.viewmodel.fare.DetermineRouteAction
import com.example.socarpaymentcalculate.viewmodel.fare.FareViewModel
import com.example.socarpaymentcalculate.viewmodel.fare.SelectCarModelAction
import com.example.socarpaymentcalculate.viewmodel.fare.SelectCarTypeAction
import com.example.socarpaymentcalculate.viewmodel.map.ClickSearchButtonAction
import com.example.socarpaymentcalculate.viewmodel.map.MapViewModel
import com.example.socarpaymentcalculate.viewmodel.map.SetDeparturePointAction
import com.example.socarpaymentcalculate.viewmodel.map.SetDestinationAction

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private lateinit var fareViewModel: FareViewModel

    private lateinit var mapViewModel: MapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fareViewModel = getViewModel(FareViewModel::class.java)
        mapViewModel = getViewModel(MapViewModel::class.java)

        setupCarTypeRecyclerView()

        setupCarModelRecyclerView()

        setupObservingData()

        setupListener()
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
                                mapViewModel.flowAction(SetDeparturePointAction(it))
                            }

                    this == R.id.tv_destination ->
                        (data?.getParcelableExtra(EXTRA_POI) as? Poi)
                            ?.also {
                                mapViewModel.flowAction(SetDestinationAction(it))
                            }

                    else ->
                        throw IllegalStateException("Unknown view id $this")
                }
            }
        }
    }

    private fun setupCarTypeRecyclerView() {
        binding.rvCarType.apply {
            LinearLayoutManager(applicationContext).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
                layoutManager = it
                addItemDecoration(DividerItemDecoration(applicationContext, it.orientation))
            }
            adapter = CarTypeAdapter(applicationContext) {
                fareViewModel.flowAction(SelectCarTypeAction(it))
            }

            setItem<CarType, CarTypeAdapter>(CarType.values().toList())
        }
    }

    private fun setupCarModelRecyclerView() {
        binding.rvCarModel.apply {
            LinearLayoutManager(applicationContext).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
                layoutManager = it
                addItemDecoration(DividerItemDecoration(applicationContext, it.orientation))
            }
            adapter = CarModelAdapter(applicationContext) {
                fareViewModel.flowAction(SelectCarModelAction(it))
            }
        }
    }

    private fun setupObservingData() {
        binding.mapView.apply {

            mapViewModel.mapFocus.observe(::setCameraFocus)

            mapViewModel.departurePointMarkerPosition.observe(::setDeparturePointMarker)

            mapViewModel.destinationMarkerPosition.observe(::setDestinationMarker)

            mapViewModel.route.observe {
                fareViewModel.flowAction(DetermineRouteAction(it))
                setRoutePolyline(it.coordinates)
            }
        }

        mapViewModel.departurePointName.observe(binding.tvDeparture::setText)

        mapViewModel.destinationName.observe(binding.tvDestination::setText)

        fareViewModel.carModelList.observe {
            binding.rvCarModel.setItem<CarModel, CarModelAdapter>(it)
        }

        fareViewModel.calculatedFare.observe(binding.tvFare::setText)
    }

    private fun setupListener() {
        binding.btnSearch.setClickListener {
            mapViewModel.flowAction(ClickSearchButtonAction())
        }
    }
}