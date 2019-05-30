package com.example.socarpaymentcalculate.common

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

inline fun <I, reified T : ListAdapter<I, *>> RecyclerView.setItem(item: List<I>?) {
    if (item != null) {
        (this.adapter as T).submitList(item)
    }
}
