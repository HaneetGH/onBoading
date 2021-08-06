package com.haneet.assignment.ui.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil

import com.haneet.assignment.R
import com.haneet.assignment.base.BaseClass
import com.haneet.assignment.constant.Task
import com.haneet.assignment.data.data_model.LocationTable
import com.haneet.assignment.databinding.ActivityDateLoginBinding
import com.haneet.assignment.domain.DataState
import com.haneet.assignment.interfaces.RecyclerViewClickListener
import com.haneet.assignment.ui.MainActivity
import com.haneet.assignment.ui.list.adapter.ListAdapter
import com.haneet.assignment.ui.location.LocationFetchFromMapActivity
import com.haneet.assignment.utils.ListDiffCallback

class ListActivity : BaseClass() {


    private val viewModel by viewModels<ListActivityViewModel>()
    lateinit var binding: ActivityDateLoginBinding
    lateinit var listAdapter: ListAdapter;
    private val listOfLocations: ArrayList<LocationTable> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun setBinding() {
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_date_login)
        binding.counter = 60.00

        binding.handler = ClickEvents()
        setEvents();
        setAdapter();
    }

    private fun setAdapter() {
        listAdapter = ListAdapter(listOfLocations, this) { v, position -> }
        binding.adapter = listAdapter
    }

    private fun setEvents() {

    }


    override fun attachViewModel() {
        viewModel.setStateEvent(MainListStateEvent.FetchBookmark)
        viewModel.uiState.observe(this, Observer { parse(it) })
    }

    override fun onResume() {
        super.onResume()
        viewModel?.setStateEvent(MainListStateEvent.FetchBookmark)
    }

    private fun parse(it: DataState?) {

        if (it != null) {
            when (it) {
                is DataState.Success<*> -> {
                    Log.d("Api Response", "SUCCES");

                    if (it?.data != null) {
                        when (it.task) {
                            Task.FETCH -> {
                                try {
                                    val value = it.data as List<LocationTable>
                                    if (value.isNotEmpty())
                                        setData(value)
                                    else
                                        binding.isListHere = false
                                    Log.d("Api Response", value.toString())
                                } catch (e: Exception) {

                                }
                            }

                        }

                    }

                }
                is DataState.Error -> {

                    Log.d("Api Response", "ERROR ${it.exception.toString()}");
                }
                is DataState.Loading -> {
                    Log.d("Api Response", "LOADING $it");


                }
            }
        }


    }

    fun setData(locationTable: List<LocationTable>) {
        binding.isListHere = true
        val diffCallback = ListDiffCallback(listOfLocations, locationTable)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listOfLocations.clear()
        listOfLocations.addAll(locationTable)
        diffResult.dispatchUpdatesTo(listAdapter)
    }


    inner class ClickEvents() {

        fun openMap() {
            var intent =
                Intent(this@ListActivity, LocationFetchFromMapActivity::class.java)
            startActivity(intent)
        }
    }


}


