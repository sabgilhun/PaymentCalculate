package com.example.socarpaymentcalculate.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.socarpaymentcalculate.databinding.ItemTextBinding

class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding: ItemTextBinding = requireNotNull(DataBindingUtil.bind(itemView))

}