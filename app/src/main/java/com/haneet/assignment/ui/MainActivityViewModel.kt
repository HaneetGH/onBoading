package com.haneet.assignment.ui

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haneet.assignment.data.repository.MainActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: MainActivityRepository
) : ViewModel() {

    var
            _uiStateFeatured: MutableLiveData<Int> =
        MutableLiveData()


    private val _downloading: MutableLiveData<Boolean> = MutableLiveData()
    val downloading: LiveData<Boolean> = _downloading
    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {


                is MainStateEvent.DownloadIt -> {

                }

            }

        }
    }








}

sealed class MainStateEvent {

    data class DownloadIt(var url: String, var context: Activity) : MainStateEvent()
    object None : MainStateEvent()
}

