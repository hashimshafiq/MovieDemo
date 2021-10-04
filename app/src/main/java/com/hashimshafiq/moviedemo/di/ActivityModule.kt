package com.hashimshafiq.moviedemo.di

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule{
    @Provides
    fun provideLinearLayoutManager(@ApplicationContext context: Context): LinearLayoutManager = LinearLayoutManager(context)
}