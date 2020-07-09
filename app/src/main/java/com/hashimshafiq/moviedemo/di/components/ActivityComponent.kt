package com.hashimshafiq.moviedemo.di.components

import com.hashimshafiq.moviedemo.di.ActivityScope
import com.hashimshafiq.moviedemo.di.modules.ActivityModule
import com.hashimshafiq.moviedemo.ui.detail.MovieDetailActivity
import com.hashimshafiq.moviedemo.ui.home.HomeActivity
import com.hashimshafiq.moviedemo.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class],
            modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity : SplashActivity)
    fun inject(activity : HomeActivity)
    fun inject(activity : MovieDetailActivity)
}