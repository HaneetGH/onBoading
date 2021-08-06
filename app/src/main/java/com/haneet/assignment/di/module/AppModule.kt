package com.haneet.assignment.di.module

import android.content.Context
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    open fun picasso(
        @ApplicationContext app: Context
    ): Picasso {
        return Picasso.Builder(app.applicationContext)
            .downloader(OkHttp3Downloader(app))
            .loggingEnabled(true)
            .build()
    }
}