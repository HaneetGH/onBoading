package com.haneet.assignment.ui.onboarding

import android.content.Context

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.haneet.assignment.base.BaseViewModel
import com.haneet.assignment.data.repository.MainActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi

import javax.inject.Inject
@HiltViewModel
class ListActivityViewModel @Inject constructor(
    private val repository: MainActivityRepository

) : ViewModel() {
    var count = MutableLiveData<Double>()
    var reset = ObservableBoolean()
    var otpGenerated = MutableLiveData<Boolean>()
    var otp = 1234
    var phone = ""
    internal var loginFlow = MutableLiveData<Boolean>()



}