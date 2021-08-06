package com.haneet.assignment.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.gms.maps.model.LatLng
import com.haneet.assignment.R
import com.haneet.assignment.base.BaseClass
import com.haneet.assignment.databinding.ActivityMainBinding


class MainActivity : BaseClass() {

    private val viewModel by viewModels<MainActivityViewModel>()
    private var page = 1;
    private lateinit var binding: ActivityMainBinding
    var lat: Double = 0.0
    var lng: Double = 0.0
    var latLng: LatLng = LatLng(0.0, 0.0)
    var inflater: LayoutInflater? = null

    override fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflater = this.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater

        getIntentData(intent)
        viewModel.setStateEvent(
            MainStateEvent.FetchWeather(
                latLng
            )
        )
    }


    private fun getIntentData(intent: Intent?) {
        if (intent != null) {
            lat = intent.getDoubleExtra("lat", 0.0)
            lng = intent.getDoubleExtra("lng", 0.0)
            latLng = LatLng(lat, lng)
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStop() {
        super.onStop()

    }

    override fun attachViewModel() {
        viewModel._uiStateFeatured.observe(this, Observer { parse(it) })


    }


    private fun parse(it: Int?) {
        if (it != null) {


        }

    }


}
