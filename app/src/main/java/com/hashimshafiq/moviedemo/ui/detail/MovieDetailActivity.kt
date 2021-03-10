package com.hashimshafiq.moviedemo.ui.detail

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.hashimshafiq.moviedemo.data.local.db.entity.Movie
import com.hashimshafiq.moviedemo.databinding.ActivityPostDetailBinding
import com.hashimshafiq.moviedemo.di.components.ActivityComponent
import com.hashimshafiq.moviedemo.ui.base.BaseActivity
import com.hashimshafiq.moviedemo.utils.common.Constants

class MovieDetailActivity : BaseActivity<MovieDetailViewModel>() {

    companion object {
        const val MOVIE_DATA = "movie"
    }

    lateinit var binding : ActivityPostDetailBinding

    override fun provideLayoutId(): View {
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun injectDependencies(activityComponent: ActivityComponent){
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?){
        val movie = intent.extras?.getParcelable<Movie>(MOVIE_DATA)
        ?: throw IllegalArgumentException("post must be non-null")

        Glide
                .with(applicationContext)
                .load(Constants.IMAGE_BASE_UR+movie.poster_path)
                .into(binding.ivMovie)


    }
}