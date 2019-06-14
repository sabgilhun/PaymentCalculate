package com.example.socarpaymentcalculate.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["setImage"])
fun ImageView.setImageByResourceId(resourceId: Int) {
    setImageResource(resourceId)
}