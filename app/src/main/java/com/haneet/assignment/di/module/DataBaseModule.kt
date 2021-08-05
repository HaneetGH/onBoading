package com.haneet.assignment.di.module

import androidx.room.Room
import com.haneet.assignment.application.MyApp
import com.haneet.assignment.data.room.database.Database
import com.haneet.assignment.data.room.database.dao.LocationDao

import com.haneet.assignment.di.scopes.ApplicationScoped

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @ApplicationScoped
    fun provideDatabase(context: MyApp): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,"test"
        )
            .fallbackToDestructiveMigration()
            // .addMigrations(WhideDatabase.MIGRATION_1_2) /*.allowMainThreadQueries()*/
            .build()
    }




    @ApplicationScoped
    @Provides
    fun provideUseLocationDao(database: Database): LocationDao {
        return database.getLocationMaster()
    }



}