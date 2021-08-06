package com.haneet.assignment.ui.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DiffUtil

import com.haneet.assignment.R
import com.haneet.assignment.base.BaseClass
import com.haneet.assignment.constant.Task
import com.haneet.assignment.data.data_model.LocationTable
import com.haneet.assignment.databinding.ActivityDateLoginBinding
import com.haneet.assignment.databinding.ListActivityBinding
import com.haneet.assignment.domain.DataState
import com.haneet.assignment.interfaces.RecyclerViewClickListener
import com.haneet.assignment.ui.MainActivity
import com.haneet.assignment.ui.list.adapter.ListAdapter
import com.haneet.assignment.ui.location.LocationFetchFromMapActivity
import com.haneet.assignment.utils.ListDiffCallback

class ListActivity : BaseClass() {


    private val viewModel by viewModels<ListActivityViewModel>()
    lateinit var binding: ListActivityBinding


    override fun setBinding() {
        binding =
            DataBindingUtil.setContentView(this, R.layout.list_activity)
        binding.bottomNavigation.setupWithNavController(Navigation.findNavController(this, R.id.fragmentContainerView))

    }


    override fun attachViewModel() {
        //viewModel.setStateEvent(MainListStateEvent.FetchBookmark)
        //    viewModel.uiState.observe(this, Observer { parse(it) })
    }


}


