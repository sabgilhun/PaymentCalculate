package com.example.socarpaymentcalculate.view

import android.os.Bundle
import com.example.socarpaymentcalculate.R
import com.example.socarpaymentcalculate.databinding.ActivityHomeBinding
import com.example.socarpaymentcalculate.view.base.BaseActivity
import com.example.socarpaymentcalculate.viewmodel.FareViewModel
import com.example.socarpaymentcalculate.viewmodel.MapViewModel
import com.example.socarpaymentcalculate.viewmodel.SearchSettingViewModel

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private lateinit var mapViewModel: MapViewModel

    private lateinit var fareViewModel: FareViewModel

    private lateinit var searchSettingViewModel: SearchSettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mapViewModel = getViewModel(MapViewModel::class.java)
        fareViewModel = getViewModel(FareViewModel::class.java)
        searchSettingViewModel = getViewModel(SearchSettingViewModel::class.java)

    }

}
