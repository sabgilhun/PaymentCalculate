package com.example.socarpaymentcalculate.common

import android.widget.TextView
import com.example.socarpaymentcalculate.R

fun TextView.setColoredText(text: String) {
    this.text = text
    this.setTextColor(this.context.getColor(R.color.textBlack))
}