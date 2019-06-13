package com.example.socarpaymentcalculate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.socarpaymentcalculate.R
import com.example.socarpaymentcalculate.data.enums.CarModel
import com.example.socarpaymentcalculate.data.enums.CarType

class CarModelAdapter(
    private val context: Context,
    private val onItemClickListener: ((item: CarModel) -> Unit)?,
    private val onBindViewHolderListener: ((view: View, item: CarModel) -> Unit)?
) : ListAdapter<CarModel, TextViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TextViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
    )

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.binding.tv.text = context.getText(getItem(position).nameRes)

        onBindViewHolderListener?.invoke(holder.itemView, getItem(position))

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(getItem(position))
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CarModel>() {
            override fun areItemsTheSame(oldItem: CarModel, newItem: CarModel): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: CarModel, newItem: CarModel): Boolean {
                return oldItem == newItem
            }
        }
    }

}