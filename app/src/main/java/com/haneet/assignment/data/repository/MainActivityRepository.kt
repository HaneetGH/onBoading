package com.haneet.assignment.data.repository


import android.content.Context
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.haneet.assignment.constant.Task
import com.haneet.assignment.data.WeatherApi
import com.haneet.assignment.data.room.database.dao.LocationDao
import com.haneet.assignment.domain.DataState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject


class MainActivityRepository @Inject constructor(
    @ApplicationContext context: Context, private val weatherApi: WeatherApi
) : BaseRepository() {
    private val appContext = context.applicationContext
    var API_KEY = "fae7190d7e6433ec3a45285ffcf55c86"

    suspend fun fetchWeather(
        latLng: LatLng
    ): Flow<DataState> {
        return flow {
            emit(DataState.Loading(Task.FETCH_WEATHER))
            // var response: VehicleCategoriesList = null
            try {
                var response = weatherApi.getWeatherData(
                    latLng!!.latitude,
                    latLng!!.longitude,
                    "metric",
                    API_KEY
                )
                emit(DataState.Success(response, Task.FETCH_WEATHER))
            } catch (e: Exception) {
                Log.e("fetch erroe",e.message.toString());
            }


        }.catch {
            emit(
                DataState.ErrorThrowable(
                    it,
                    Task.FETCH_WEATHER
                )
            )
        } // Use the IO thread for this Flow // Use the IO thread for this Flow // Use the IO thread for this Flow
    }


}

