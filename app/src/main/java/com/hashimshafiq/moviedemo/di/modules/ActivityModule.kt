package com.hashimshafiq.moviedemo.di.modules

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

//@Module
//class ActivityModule(private val activity : BaseActivity<*>) {
//
//    @Provides
//    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)
//
//
//    @Provides
//    fun provideMovieDetailViewModel(
//            networkHelper: NetworkHelper
//    ): MovieDetailViewModel = ViewModelProvider(
//            activity, ViewModelProviderFactory(MovieDetailViewModel::class) {
//        MovieDetailViewModel(networkHelper)
//    }).get(MovieDetailViewModel::class.java)
//
//
//    @Provides
//    fun provideHomeViewModel(
//        networkHelper: NetworkHelper,
//        homeRepository: HomeRepository
//    ): HomeViewModel = ViewModelProvider(
//            activity, ViewModelProviderFactory(HomeViewModel::class) {
//        HomeViewModel(networkHelper,homeRepository)
//    }).get(HomeViewModel::class.java)
//
//}

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule{
    @Provides
    fun provideLinearLayoutManager(@ApplicationContext context: Context): LinearLayoutManager = LinearLayoutManager(context)
}