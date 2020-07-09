package com.hashimshafiq.moviedemo.di.components

import android.app.Application
import android.content.Context
import com.hashimshafiq.moviedemo.MovieApplication
import com.hashimshafiq.moviedemo.data.local.db.DatabaseService
import com.hashimshafiq.moviedemo.data.remote.NetworkService
import com.hashimshafiq.moviedemo.di.ApplicationContext
import com.hashimshafiq.moviedemo.di.modules.ApplicationModule
import com.hashimshafiq.moviedemo.utils.network.NetworkHelper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: MovieApplication)

    fun getApplication() : Application

    @ApplicationContext
    fun getContext() : Context

    fun getNetworkService() : NetworkService

    fun getDatabaseService() : DatabaseService

    fun getNetworkHelper(): NetworkHelper


}