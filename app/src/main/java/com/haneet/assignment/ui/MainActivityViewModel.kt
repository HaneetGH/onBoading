package com.haneet.assignment.ui

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haneet.assignment.data.repository.MainActivityRepository
import com.haneet.assignment.domain.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: MainActivityRepository
) : ViewModel() {

    var
            _uiStateFeatured: MutableLiveData<Int> =
        MutableLiveData()
    private val _uiState: MutableLiveData<DataState> = MutableLiveData()
    val uiState: MutableLiveData<DataState> get() = _uiState

    private val _downloading: MutableLiveData<Boolean> = MutableLiveData()
    val downloading: LiveData<Boolean> = _downloading
    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.DownloadIt -> {
                    repository.fetchBookmark(
                    ).collect { uiState.value = it }
                }

            }

        }
    }


}

sealed class MainStateEvent {

    data class DownloadIt(var url: String, var context: Activity) : MainStateEvent()
    object None : MainStateEvent()
}

