package com.example.socarpaymentcalculate

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.socarpaymentcalculate.adapter.PoiAdapter
import com.example.socarpaymentcalculate.data.model.Poi

object BindingAdapter {
    @BindingAdapter("item")
    @JvmStatic
    fun setRecyclerViewItem(view: RecyclerView, item: List<Poi>?) {
        if (item != null) {
            (view.adapter as PoiAdapter).submitList(item)
        }
    }
}