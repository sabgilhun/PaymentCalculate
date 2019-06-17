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
import com.example.socarpaymentcalculate.adapter.CarTypeAdapter
import com.example.socarpaymentcalculate.adapter.FareAdapter
import com.example.socarpaymentcalculate.common.setItem
import com.example.socarpaymentcalculate.data.enums.CarType
import com.example.socarpaymentcalculate.data.model.Fare
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.databinding.ActivityHomeBinding
import com.example.socarpaymentcalculate.view.base.BaseActivity
import com.example.socarpaymentcalculate.viewmodel.fare.DetermineRouteAction
import com.example.socarpaymentcalculate.viewmodel.fare.FareViewModel
import com.example.socarpaymentcalculate.viewmodel.fare.SelectCarTypeAction
import com.example.socarpaymentcalculate.viewmodel.map.MapViewModel
import com.example.socarpaymentcalculate.viewmodel.map.SetEndPointAction
import com.example.socarpaymentcalculate.viewmodel.map.SetStartPointAction
import com.google.android.material.bottomsheet.BottomSheetBehavior

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private lateinit var fareViewModel: FareViewModel

    private lateinit var mapViewModel: MapViewModel

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fareViewModel = getViewModel(FareViewModel::class.java)
        mapViewModel = getViewModel(MapViewModel::class.java)

        setupBottomSheetStateHandler()

        setupCarTypeRecyclerView()

        setupCarModelRecyclerView()

        setupObservingData()
    }

    fun onClickSearchTextBox(view: View) {
        val intent = when {
            view.id == R.id.tv_startPoint ->
                Intent(this, SearchActivity::class.java)
                    .putExtra(EXTRA_VIEW_ID, R.id.tv_startPoint)

            view.id == R.id.tv_endPoint ->
                Intent(this, SearchActivity::class.java)
                    .putExtra(EXTRA_VIEW_ID, R.id.tv_endPoint)

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
                    this == R.id.tv_startPoint ->
                        (data?.getParcelableExtra(EXTRA_POI) as? Poi)
                            ?.also {
                                mapViewModel.flowAction(SetStartPointAction(it))
                            }

                    this == R.id.tv_endPoint ->
                        (data?.getParcelableExtra(EXTRA_POI) as? Poi)
                            ?.also {
                                mapViewModel.flowAction(SetEndPointAction(it))
                            }

                    else ->
                        throw IllegalStateException("Unknown view id $this")
                }
            }
        }
    }

    private fun setupCarTypeRecyclerView() {
        bind {
            rvCarType.apply {
                LinearLayoutManager(applicationContext).also {
                    it.orientation = LinearLayoutManager.HORIZONTAL
                    layoutManager = it
                    addItemDecoration(DividerItemDecoration(applicationContext, it.orientation))
                }

                adapter = CarTypeAdapter(applicationContext, {
                    fareViewModel.flowAction(SelectCarTypeAction(it))
                }, { view, carType ->
                    fareViewModel.selectedCarType.observe {
                        view.isSelected = (it == carType)
                    }
                })

                setItem<CarType, CarTypeAdapter>(CarType.values().toList())
            }
        }
    }

    private fun setupCarModelRecyclerView() {
        binding.rvCarModel.apply {
            LinearLayoutManager(applicationContext).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
                layoutManager = it
                addItemDecoration(DividerItemDecoration(applicationContext, it.orientation))
            }
            adapter = FareAdapter()
        }
    }

    private fun setupObservingData() {
        bind {
            mapViewModel.let {
                it.navigation.observe { navigation ->
                    mapView.setCameraFocus(navigation.mapFocus)
                    mapView.setStartPointMarker(navigation.startPointMarkerPosition)
                    mapView.setEndPointMarker(navigation.endPointMarkerPosition)
                    mapView.setRoutePolyline(navigation.route.coordinates)
                    fareViewModel.flowAction(DetermineRouteAction(navigation.route))
                }

                it.searchedStartPoint.observe(tvStartPoint::setText)
                it.searchedEndPoint.observe(tvEndPoint::setText)
            }

            fareViewModel.let {
                it.calculatedFare.observe { fare ->
                    rvCarModel.setItem<Fare, FareAdapter>(fare)
                }
            }
        }
    }

    private fun setupBottomSheetStateHandler() {
        bind {
            bottomSheetBehavior = BottomSheetBehavior.from(clBottomSheet)

            clBottomSheet.setOnClickListener {
                if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }

            mapView.setOnCameraMoveListener {
                if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }
        }
    }
}