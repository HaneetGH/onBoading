package com.haneet.assignment.data.repository


import android.content.Context
import android.util.Log
import com.haneet.assignment.constant.Task
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


class ListActivityRepository @Inject constructor(
    @ApplicationContext context: Context, private val locationDao: LocationDao
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
              Log.e("fetch erroe",e.message.toString());
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


}

