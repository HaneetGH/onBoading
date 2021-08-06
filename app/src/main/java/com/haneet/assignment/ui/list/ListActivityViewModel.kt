package com.haneet.assignment.ui.list

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.haneet.assignment.data.repository.ListActivityRepository
import com.haneet.assignment.domain.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

import javax.inject.Inject
@HiltViewModel
class ListActivityViewModel @Inject constructor(
    private val repository: ListActivityRepository

) : ViewModel() {
    var reset = ObservableBoolean()
    private val _uiState: MutableLiveData<DataState> = MutableLiveData()
    val uiState: MutableLiveData<DataState> get() = _uiState
    fun setStateEvent(mainStateEvent: MainListStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainListStateEvent.FetchBookmark -> {


                    repository.fetchBookmark(
                    ).collect { uiState.value = it }
                }

            }

        }
    }



}

sealed class MainListStateEvent {

    object FetchBookmark : MainListStateEvent()
    object None : MainListStateEvent()
}


