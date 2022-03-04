package com.technorapper.onboarding.data.repository


import android.content.Context
import android.util.Log
import com.technorapper.onboarding.constant.Task
import com.technorapper.onboarding.data.MyPreference
import com.technorapper.onboarding.data.data_model.LocationTable
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


class ListActivityRepository @Inject constructor(
    @ApplicationContext context: Context,
    private val locationDao: LocationDao,
    private val myPreference: MyPreference
) : BaseRepository() {
    private val appContext = context.applicationContext


    suspend fun fetchBookmark(
    ): Flow<DataState> {
        return flow {
            emit(DataState.Loading(Task.FETCH))
            // var response: VehicleCategoriesList = null
            try {
                var response = locationDao.getAllLocations()
                emit(DataState.Success(response, Task.FETCH))
            } catch (e: Exception) {
                Log.e("fetch erroe", e.message.toString());
            }


        }.flowOn(Dispatchers.IO).catch {
            emit(
                DataState.ErrorThrowable(
                    it,
                    Task.FETCH
                )
            )
        } // Use the IO thread for this Flow // Use the IO thread for this Flow // Use the IO thread for this Flow
    }

    suspend fun deleteItem(
        locationTable: LocationTable
    ): Flow<DataState> {
        return flow {
            emit(DataState.Loading(Task.DELETE))
            // var response: VehicleCategoriesList = null
            try {
                var response = locationDao.delete(locationTable)
                emit(DataState.Success(response, Task.DELETE))
            } catch (e: Exception) {
                Log.e("fetch erroe", e.message.toString());
            }


        }.flowOn(Dispatchers.IO).catch {
            emit(
                DataState.ErrorThrowable(
                    it,
                    Task.DELETE
                )
            )
        } // Use the IO thread for this Flow // Use the IO thread for this Flow // Use the IO thread for this Flow
    }

    suspend fun nukeTable(
    ): Flow<DataState> {
        return flow {
            emit(DataState.Loading(Task.NUKE))
            // var response: VehicleCategoriesList = null
            try {
                var response = locationDao.nukeLocation()
                emit(DataState.Success(response, Task.NUKE))
            } catch (e: Exception) {
                Log.e("fetch erroe", e.message.toString());
            }


        }.flowOn(Dispatchers.IO).catch {
            emit(
                DataState.ErrorThrowable(
                    it,
                    Task.NUKE
                )
            )
        } // Use the IO thread for this Flow // Use the IO thread for this Flow // Use the IO thread for this Flow
    }

    suspend fun fetchDefault(
    ): Flow<DataState> {
        return flow {
            emit(DataState.Loading(Task.DEFAULT))
            // var response: VehicleCategoriesList = null
            try {
                var response = myPreference.getStoredUnit()
                emit(DataState.Success(response, Task.DEFAULT))
            } catch (e: Exception) {
                Log.e("fetch erroe", e.message.toString());
            }


        }.flowOn(Dispatchers.IO).catch {
            emit(
                DataState.ErrorThrowable(
                    it,
                    Task.DEFAULT
                )
            )
        } // Use the IO thread for this Flow // Use the IO thread for this Flow // Use the IO thread for this Flow
    }

    suspend fun updateUnit(
        unit: Boolean
    ) {
        myPreference.setStoredUnit(if (unit) "imperial" else "metric")
    }
}

