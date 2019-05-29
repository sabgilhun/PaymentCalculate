package com.example.socarpaymentcalculate.common

import android.view.View

fun View.setClickListener(block: (View) -> Unit) {
    this.setOnClickListener {
        block(it)
    }
}