package com.example.socarpaymentcalculate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.socarpaymentcalculate.R
import com.example.socarpaymentcalculate.data.model.Poi

class PoiAdapter : ListAdapter<Poi, PoiViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PoiViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_poi, parent, false)
    )

    override fun onBindViewHolder(holder: PoiViewHolder, position: Int) {
        holder.binding.item = getItem(position)
        holder.binding.executePendingBindings()
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Poi>() {
            override fun areItemsTheSame(oldItem: Poi, newItem: Poi): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Poi, newItem: Poi): Boolean {
                return oldItem == newItem
            }
        }
    }
}