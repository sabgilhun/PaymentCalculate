package com.example.socarpaymentcalculate.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.socarpaymentcalculate.databinding.ItemFareBinding


class FareViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding: ItemFareBinding = requireNotNull(DataBindingUtil.bind(itemView))

}