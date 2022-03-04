package com.technorapper.onboarding.data.repository


import android.content.Context
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.technorapper.onboarding.constant.Task
import com.technorapper.onboarding.data.MyPreference
import com.technorapper.onboarding.data.WeatherApi
import com.technorapper.onboarding.data.room.database.dao.LocationDao
import com.technorapper.onboarding.domain.DataState
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
    @ApplicationContext context: Context, private val weatherApi: WeatherApi,private val myPreference: MyPreference
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
                    myPreference.getStoredUnit(),
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

