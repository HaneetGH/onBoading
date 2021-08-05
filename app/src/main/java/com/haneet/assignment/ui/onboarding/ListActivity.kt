package com.haneet.assignment.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil

import com.haneet.assignment.R
import com.haneet.assignment.base.BaseClass
import com.haneet.assignment.databinding.ActivityDateLoginBinding
import com.haneet.assignment.ui.MainActivity
import com.haneet.assignment.ui.location.LocationFetchFromMapActivity
import kotlinx.coroutines.InternalCoroutinesApi

class ListActivity : BaseClass() {


    private val viewModel by viewModels<ListActivityViewModel>()
    lateinit var binding: ActivityDateLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun setBinding() {
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_date_login)
        binding.counter = 60.00

        binding.handler = ClickEvents()
        setEvents();
    }

    private fun setEvents() {

    }

    @InternalCoroutinesApi
    override fun attachViewModel() {
        viewModel.reset.set(true)
        viewModel.loginFlow.observe(this, { parseLogin(it) })
        viewModel.otpGenerated.observe(this, { binding.isForOTP = it })
    }


    @InternalCoroutinesApi
    private fun parseLogin(it: Boolean?) {
        if (it == true) {
            var intent =
                Intent(this@ListActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }


    inner class ClickEvents() {

        fun openMap() {
            var intent =
                Intent(this@ListActivity, LocationFetchFromMapActivity::class.java)
            startActivity(intent)
        }
    }


}


