package com.technorapper.onboarding.data.repository


import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
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


    suspend fun registerUser(
        storedVerificationId: String, code: String
    ): Flow<DataState> {
        return flow {
            emit(DataState.Loading(Task.FETCH))
            // var response: VehicleCategoriesList = null
            val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, code)
            try {
                var result = FirebaseAuth.getInstance()!!.signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                        } else {
                            // if the code is not correct then we are
                            // displaying an error message to the user.

                        }
                    }
                emit(DataState.Success(result, Task.FETCH))


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


}

