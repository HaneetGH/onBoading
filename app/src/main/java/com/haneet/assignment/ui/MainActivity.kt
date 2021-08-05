package com.haneet.assignment.ui

import android.app.NotificationManager
import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.haneet.assignment.R
import com.haneet.assignment.base.BaseClass
import com.haneet.assignment.databinding.ActivityMainBinding

import kotlinx.coroutines.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*



class MainActivity : BaseClass() {

    private val viewModel by viewModels<MainActivityViewModel>()
    private var page = 1;
    private lateinit var binding: ActivityMainBinding


    var inflater: LayoutInflater? = null
    private var mProgressDialog: ProgressDialog? = null
    override fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.btn.setOnClickListener(View.OnClickListener {
            viewModel.setStateEvent(
                MainStateEvent.DownloadIt(
                    binding.url.text.toString(),
                    this
                )
            )
            // viewModel.listBound(this)
        })


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflater = this.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
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


            binding.txt.text = it.toString()
            binding.progressCircular.progress = it



            if (it as Int == 100) {

                binding.txt.text = "Complete"
                binding.btnOpen.visibility = View.VISIBLE

            }

        }

    }



}
