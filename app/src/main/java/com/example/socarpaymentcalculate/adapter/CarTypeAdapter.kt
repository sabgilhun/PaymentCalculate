package com.example.socarpaymentcalculate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.socarpaymentcalculate.R
import com.example.socarpaymentcalculate.data.enums.CarType

class CarTypeAdapter(
    private val context: Context,
    private val onItemClickListener: ((item: CarType) -> Unit)?
) : ListAdapter<CarType, TextViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TextViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
    )

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.binding.tv.text = context.getString(getItem(position).nameRes)
        holder.binding.executePendingBindings()

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(getItem(position))
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CarType>() {
            override fun areItemsTheSame(oldItem: CarType, newItem: CarType): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: CarType, newItem: CarType): Boolean {
                return oldItem == newItem
            }
        }
    }

}