package com.example.socarpaymentcalculate.view

import android.os.Bundle
import com.example.socarpaymentcalculate.R
import com.example.socarpaymentcalculate.databinding.ActivitySearchBinding
import com.example.socarpaymentcalculate.view.base.BaseActivity
import com.example.socarpaymentcalculate.viewmodel.SearchViewModel

class SearchActivity : BaseActivity<ActivitySearchBinding>(R.layout.activity_home) {

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchViewModel = getViewModel(SearchViewModel::class.java)

    }

}
