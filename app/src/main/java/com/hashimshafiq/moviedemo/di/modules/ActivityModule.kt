package com.hashimshafiq.moviedemo.di.modules

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hashimshafiq.moviedemo.ViewModelProviderFactory
import com.hashimshafiq.moviedemo.data.repository.HomeRepository
import com.hashimshafiq.moviedemo.ui.base.BaseActivity
import com.hashimshafiq.moviedemo.ui.detail.MovieDetailViewModel
import com.hashimshafiq.moviedemo.ui.home.HomeViewModel
import com.hashimshafiq.moviedemo.ui.home.adapter.MoviesAdapter
import com.hashimshafiq.moviedemo.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity : BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)


    @Provides
    fun provideMovieDetailViewModel(
            networkHelper: NetworkHelper
    ): MovieDetailViewModel = ViewModelProvider(
            activity, ViewModelProviderFactory(MovieDetailViewModel::class) {
        MovieDetailViewModel(networkHelper)
    }).get(MovieDetailViewModel::class.java)


    @Provides
    fun provideHomeViewModel(
        networkHelper: NetworkHelper,
        homeRepository: HomeRepository
    ): HomeViewModel = ViewModelProvider(
            activity, ViewModelProviderFactory(HomeViewModel::class) {
        HomeViewModel(networkHelper,homeRepository)
    }).get(HomeViewModel::class.java)

}