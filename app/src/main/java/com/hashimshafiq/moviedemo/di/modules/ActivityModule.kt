package com.hashimshafiq.moviedemo.di.modules

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hashimshafiq.moviedemo.utils.common.GridSpacingItemDecoration
import com.hashimshafiq.moviedemo.ViewModelProviderFactory
import com.hashimshafiq.moviedemo.data.repository.HomeRepository
import com.hashimshafiq.moviedemo.ui.base.BaseActivity
import com.hashimshafiq.moviedemo.ui.detail.MovieDetailViewModel
import com.hashimshafiq.moviedemo.ui.home.HomeViewModel
import com.hashimshafiq.moviedemo.ui.home.adapter.MoviesAdapter
import com.hashimshafiq.moviedemo.ui.splash.SplashViewModel
import com.hashimshafiq.moviedemo.utils.common.Constants
import com.hashimshafiq.moviedemo.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity : BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun providesMoviesAdapter(): MoviesAdapter = MoviesAdapter(activity.lifecycle, arrayListOf())

    @Provides
    fun provideGridLayoutManager(): GridLayoutManager = GridLayoutManager(activity, Constants.SPAN_COUNT)

    @Provides
    fun providesGridItemDecoration(): GridSpacingItemDecoration =
            GridSpacingItemDecoration(Constants.SPAN_COUNT, Constants.SPAN_SPACING, Constants.IS_EDGE_CASE)

    @Provides
    fun provideSplashViewModel(
            networkHelper: NetworkHelper
    ): SplashViewModel = ViewModelProvider(
            activity, ViewModelProviderFactory(SplashViewModel::class) {
        SplashViewModel(networkHelper)
        //this lambda creates and return SplashViewModel
    }).get(SplashViewModel::class.java)

    @Provides
    fun provideMovieDetailViewModel(
            networkHelper: NetworkHelper
    ): MovieDetailViewModel = ViewModelProvider(
            activity, ViewModelProviderFactory(MovieDetailViewModel::class) {
        MovieDetailViewModel(networkHelper)
        //this lambda creates and return SplashViewModel
    }).get(MovieDetailViewModel::class.java)


    @Provides
    fun provideHomeViewModel(
        networkHelper: NetworkHelper,
        homeRepository: HomeRepository
    ): HomeViewModel = ViewModelProvider(
            activity, ViewModelProviderFactory(HomeViewModel::class) {
        HomeViewModel(networkHelper,homeRepository)
        //this lambda creates and return SplashViewModel
    }).get(HomeViewModel::class.java)

}