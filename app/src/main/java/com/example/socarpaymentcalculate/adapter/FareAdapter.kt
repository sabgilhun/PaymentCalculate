package com.example.socarpaymentcalculate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.socarpaymentcalculate.R
import com.example.socarpaymentcalculate.data.model.Fare

class FareAdapter : ListAdapter<Fare, FareViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FareViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_fare, parent, false)
    )

    override fun onBindViewHolder(holder: FareViewHolder, position: Int) {
        holder.binding.fare = getItem(position)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Fare>() {
            override fun areItemsTheSame(oldItem: Fare, newItem: Fare): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Fare, newItem: Fare): Boolean {
                return oldItem == newItem
            }
        }
    }
}