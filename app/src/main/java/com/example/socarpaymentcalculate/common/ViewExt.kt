package com.example.socarpaymentcalculate.common

import android.view.View

fun View.setOnClickListner(block: (View) -> Unit) {
    this.setOnClickListener {
        block(it)
    }
}