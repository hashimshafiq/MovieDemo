package com.hashimshafiq.moviedemo

import android.app.Application
import com.hashimshafiq.moviedemo.di.components.ApplicationComponent
import com.hashimshafiq.moviedemo.di.components.DaggerApplicationComponent
import com.hashimshafiq.moviedemo.di.modules.ApplicationModule

class MovieApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }


    private fun injectDependencies(){
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()

        applicationComponent.inject(this)
    }


}