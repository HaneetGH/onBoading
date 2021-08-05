package com.haneet.assignment.data.repository


import android.content.Context
import android.util.Log
import com.haneet.assignment.constant.Task
import com.haneet.assignment.domain.DataState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject


class MainActivityRepository @Inject constructor(
    @ApplicationContext context: Context
) : BaseRepository() {
    private val appContext = context.applicationContext













}

