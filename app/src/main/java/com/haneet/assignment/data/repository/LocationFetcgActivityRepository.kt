package com.haneet.assignment.data.repository


import android.content.Context
import com.haneet.assignment.data.data_model.LocationTable
import com.haneet.assignment.data.room.database.dao.LocationDao
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class LocationFetcgActivityRepository @Inject constructor(
    @ApplicationContext context: Context, private val locationDao: LocationDao
) : BaseRepository() {
    suspend fun saveInDb(locationTable: LocationTable) {
        locationDao.insert(locationTable)
    }

    private val appContext = context.applicationContext


}

