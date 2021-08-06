package com.haneet.assignment.ui.list

import androidx.databinding.DataBindingUtil
import com.haneet.assignment.R
import com.haneet.assignment.base.BaseClass
import com.haneet.assignment.databinding.ActivityDateLoginBinding

class ListMainActivity:BaseClass() {
    lateinit var binding: ActivityDateLoginBinding

    override fun setBinding() {
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_date_login)
    }

    override fun attachViewModel() {
        TODO("Not yet implemented")
    }


}