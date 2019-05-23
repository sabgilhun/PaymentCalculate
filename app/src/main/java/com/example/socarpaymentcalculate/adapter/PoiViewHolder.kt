package com.example.socarpaymentcalculate.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.socarpaymentcalculate.databinding.ItemPoiBinding

class PoiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding: ItemPoiBinding = requireNotNull(DataBindingUtil.bind(itemView))

}