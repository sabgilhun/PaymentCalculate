package com.example.socarpaymentcalculate

import android.app.Application
import com.facebook.stetho.Stetho

class PaymentCalcApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}