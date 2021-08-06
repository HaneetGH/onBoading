package com.haneet.assignment.ui.location

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.haneet.assignment.data.data_model.LocationTable
import com.haneet.assignment.data.repository.LocationFetcgActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class LocationFetchViewModel @Inject constructor(
    private val repository: LocationFetcgActivityRepository

) : ViewModel() {
    var done = MutableLiveData<Boolean>()
    fun saveAddress(locationTable: LocationTable) {


        var jobOne = CoroutineScope(Dispatchers.IO).launch {
            val stepOne = async { repository.saveInDb(locationTable) }.await()
            done.postValue(true)
        }

    }


}