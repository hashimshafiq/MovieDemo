package com.hashimshafiq.moviedemo.ui.detail

import android.os.Bundle
import com.bumptech.glide.Glide
import com.hashimshafiq.moviedemo.R
import com.hashimshafiq.moviedemo.data.local.db.entity.Movie
import com.hashimshafiq.moviedemo.di.components.ActivityComponent
import com.hashimshafiq.moviedemo.ui.base.BaseActivity
import com.hashimshafiq.moviedemo.utils.common.Constants
import kotlinx.android.synthetic.main.activity_post_detail.*

class MovieDetailActivity : BaseActivity<MovieDetailViewModel>() {

    companion object {
        const val MOVIE_DATA = "movie"
    }
    override fun provideLayoutId(): Int = R.layout.activity_post_detail

    override fun injectDependencies(activityComponent: ActivityComponent){
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?){
        val movie = intent.extras?.getParcelable<Movie>(MOVIE_DATA)
        ?: throw IllegalArgumentException("post must be non-null")

        Glide
                .with(applicationContext)
                .load(Constants.IMAGE_BASE_UR+movie.poster_path)
                .into(ivMovie)


    }
}