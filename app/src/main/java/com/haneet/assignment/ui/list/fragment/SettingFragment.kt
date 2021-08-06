package com.haneet.assignment.ui.list.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil

import com.haneet.assignment.R
import com.haneet.assignment.base.BaseClass
import com.haneet.assignment.base.BaseFragment
import com.haneet.assignment.constant.Task
import com.haneet.assignment.data.data_model.LocationTable
import com.haneet.assignment.databinding.ActivityDateLoginBinding
import com.haneet.assignment.databinding.FragmentBlankTwoBinding
import com.haneet.assignment.domain.DataState
import com.haneet.assignment.ui.MainActivity
import com.haneet.assignment.ui.list.ListActivityViewModel
import com.haneet.assignment.ui.list.MainListStateEvent
import com.haneet.assignment.ui.list.adapter.ListAdapter
import com.haneet.assignment.ui.location.LocationFetchFromMapActivity
import com.haneet.assignment.utils.ListDiffCallback

class SettingFragment : BaseFragment() {


    private val viewModel by viewModels<ListActivityViewModel>()
    lateinit var binding: FragmentBlankTwoBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_blank_two,
            container,
            false
        )
        binding.handler = ClickEvents()
        binding.swich.setOnCheckedChangeListener { compoundButton, b ->
            viewModel.setStateEvent(
                MainListStateEvent.UpdateUnit(b)
            )
        }
        return binding.root
    }


    override fun attachViewModel() {
        viewModel.setStateEvent(MainListStateEvent.FetchDefault)
        viewModel.uiState.observe(this, Observer { parse(it) })
    }

    private fun parse(it: DataState?) {

        if (it != null) {
            when (it) {
                is DataState.Success<*> -> {
                    Log.d("Api Response", "SUCCES");

                    if (it?.data != null) {
                        when (it.task) {
                            Task.NUKE -> {
                                Toast.makeText(activity, "Success", Toast.LENGTH_LONG).show()
                            }
                            Task.DEFAULT -> {
                                val value = it.data as String

                                binding.swich.isChecked = value.equals("imperial", false)
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

    override fun onResume() {
        super.onResume()

    }


    inner class ClickEvents() {

        fun reset() {
            viewModel.setStateEvent(MainListStateEvent.Reset)
        }

        fun help() {

        }

    }


}


