package com.hashimshafiq.moviedemo.di

import android.content.Context
import androidx.room.Room
import com.hashimshafiq.moviedemo.BuildConfig
import com.hashimshafiq.moviedemo.data.local.db.DatabaseService
import com.hashimshafiq.moviedemo.data.remote.NetworkService
import com.hashimshafiq.moviedemo.data.remote.Networking
import com.hashimshafiq.moviedemo.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {


    @Singleton
    @Provides
    fun providesNetworkService(@ApplicationContext context: Context) : NetworkService =
            Networking.create(
                BuildConfig.API_KEY,
                BuildConfig.BASE_URL,
                context.cacheDir,
                10*1024*1024 //10MB
            )


    @Singleton
    @Provides
    fun provideDatabaseService(@ApplicationContext context: Context): DatabaseService =
            Room.databaseBuilder(
                context, DatabaseService::class.java,
                    "movie-demo-db"
            ).build()


    @Singleton
    @Provides
    fun providesNetworkHelper(@ApplicationContext context: Context) = NetworkHelper(context)


}