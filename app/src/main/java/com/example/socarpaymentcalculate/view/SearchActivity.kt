package com.example.socarpaymentcalculate.view

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.socarpaymentcalculate.Constants.EXTRA_POI
import com.example.socarpaymentcalculate.Constants.EXTRA_VIEW_ID
import com.example.socarpaymentcalculate.R
import com.example.socarpaymentcalculate.adapter.PoiAdapter
import com.example.socarpaymentcalculate.common.setItem
import com.example.socarpaymentcalculate.common.setTextChangeListener
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.databinding.ActivitySearchBinding
import com.example.socarpaymentcalculate.view.base.BaseActivity
import com.example.socarpaymentcalculate.viewmodel.search.SearchButtonClickAction
import com.example.socarpaymentcalculate.viewmodel.search.SearchKeywordChangeAction
import com.example.socarpaymentcalculate.viewmodel.search.SearchViewModel

class SearchActivity : BaseActivity<ActivitySearchBinding>(R.layout.activity_search) {

    @IdRes
    private var selectedView: Int = 0

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressBar = binding.pgSearch

        window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            statusBarColor = Color.WHITE
        }

        selectedView = intent.getIntExtra(EXTRA_VIEW_ID, 0)

        if (selectedView == 0) {
            throw IllegalStateException("No exist selected view data")
        }

        searchViewModel = getViewModel(SearchViewModel::class.java)

        setupPoiRecyclerView()

        setupObservingData()

        setupListener()
    }

    private fun finishActivityAfterItemSelection(item: Poi) {
        setResult(Activity.RESULT_OK, Intent().apply {
            putExtra(EXTRA_POI, item)
            putExtra(EXTRA_VIEW_ID, selectedView)
        })
        finish()
    }

    private fun setupPoiRecyclerView() {
        bind {
            rvPois.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                rvPois.adapter = PoiAdapter {
                    finishActivityAfterItemSelection(it)
                }
            }
        }
    }

    private fun setupObservingData() {
        bind {
            searchViewModel.let { vm ->
                vm.searchedPois.observe {
                    rvPois.setItem<Poi, PoiAdapter>(it)
                }

                vm.isLoading.observe(::toggleProgressBar)

                vm.errorMessage.observe(::showToastMessage)
            }
        }

    }

    private fun setupListener() {
        bind {
            ivBackButton.setOnClickListener {
                finish()
            }

            etSearch.setTextChangeListener {
                searchViewModel.flowAction(SearchKeywordChangeAction(it))
            }

            ivSearch.setOnClickListener {
                searchViewModel.flowAction(SearchButtonClickAction())
            }
        }
    }

}
