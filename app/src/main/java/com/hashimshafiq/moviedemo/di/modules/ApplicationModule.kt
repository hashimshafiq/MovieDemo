package com.hashimshafiq.moviedemo.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.hashimshafiq.moviedemo.BuildConfig
import com.hashimshafiq.moviedemo.MovieApplication
import com.hashimshafiq.moviedemo.data.local.db.DatabaseService
import com.hashimshafiq.moviedemo.data.remote.NetworkService
import com.hashimshafiq.moviedemo.data.remote.Networking
import com.hashimshafiq.moviedemo.di.ApplicationContext
import com.hashimshafiq.moviedemo.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application : MovieApplication) {

    @Singleton
    @Provides
    fun provideApplication(): Application = application


    @Singleton
    @Provides
    @ApplicationContext
    fun provideContext(): Context = application

    @Singleton
    @Provides
    fun providesNetworkService() : NetworkService =
            Networking.create(
                BuildConfig.API_KEY,
                BuildConfig.BASE_URL,
                application.cacheDir,
                10*1024*1024 //10MB
            )

    @Provides
    @Singleton
    fun provideDatabaseService(): DatabaseService =
            Room.databaseBuilder(
                    application, DatabaseService::class.java,
                    "movie-popcorn-db"
            ).build()

    @Singleton
    @Provides
    fun providesNetworkHelper() = NetworkHelper(application)


}