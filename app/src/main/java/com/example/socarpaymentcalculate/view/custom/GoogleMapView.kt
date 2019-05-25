package com.example.socarpaymentcalculate.view.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

open class GoogleMapView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), OnMapReadyCallback {

    protected var googleMap: GoogleMap? = null

    init {
        val mapFragment = SupportMapFragment.newInstance()

        if (!isInEditMode) {
            (context as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .add(id, mapFragment)
                .commit()
            setMapAsync(mapFragment)
        }
    }

    private fun setMapAsync(mapFragment: SupportMapFragment) {
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
    }
}