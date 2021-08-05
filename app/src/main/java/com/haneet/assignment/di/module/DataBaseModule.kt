package com.haneet.assignment.di.module

import android.content.Context
import androidx.room.Room
import com.haneet.assignment.application.MyApp
import com.haneet.assignment.data.room.database.Database
import com.haneet.assignment.data.room.database.dao.LocationDao

import com.haneet.assignment.di.scopes.ApplicationScoped

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        Database::class.java,
        "your_db_name"
    ).build() // The reason we can construct a database for the repo

    @Singleton
    @Provides
    fun provideYourDao(db: Database) =
        db.getLocationMaster() // The reason we can implement a Dao for the database

}
