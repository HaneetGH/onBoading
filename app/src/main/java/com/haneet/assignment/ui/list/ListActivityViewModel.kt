package com.haneet.assignment.ui.list

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haneet.assignment.data.data_model.LocationTable

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

                is MainListStateEvent.DeleteItem -> {
                    repository.deleteItem(
                        mainStateEvent.locationTable
                    ).collect { uiState.value = it }
                }
                is MainListStateEvent.Reset -> {
                    repository.nukeTable(
                    ).collect { uiState.value = it }
                }
                is MainListStateEvent.UpdateUnit -> {
                    repository.updateUnit(
                        mainStateEvent.which
                    )

                }
                is MainListStateEvent.FetchDefault -> {
                    repository.fetchDefault(
                    ).collect { uiState.value = it }

                }
            }

        }
    }


}

sealed class MainListStateEvent {

    object FetchBookmark : MainListStateEvent()
    data class DeleteItem(val locationTable: LocationTable) : MainListStateEvent()
    object None : MainListStateEvent()
    object Reset : MainListStateEvent()
    data class UpdateUnit(val which: Boolean) : MainListStateEvent()
    object FetchDefault : MainListStateEvent()
}


