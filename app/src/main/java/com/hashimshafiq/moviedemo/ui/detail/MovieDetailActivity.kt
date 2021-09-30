package com.hashimshafiq.moviedemo.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.hashimshafiq.moviedemo.data.local.db.entity.Movie
import com.hashimshafiq.moviedemo.databinding.ActivityPostDetailBinding
import com.hashimshafiq.moviedemo.utils.common.Constants
import com.hashimshafiq.moviedemo.utils.display.Toaster
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_DATA = "movie"
    }

    lateinit var binding : ActivityPostDetailBinding

    val viewModel : MovieDetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()
        setupView()
        viewModel.onCreate()
    }



    private fun showMessage(message: String) = Toaster.show(applicationContext, message)

    private fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))


    private fun setupView() {
        val movie = intent.extras?.getParcelable<Movie>(MOVIE_DATA)
        ?: throw IllegalArgumentException("post must be non-null")

        Glide
                .with(applicationContext)
                .load(Constants.IMAGE_BASE_URL+movie.poster_path)
                .into(binding.ivMovie)


    }

    private fun setupObservers(){
        viewModel.messageString.observe(this, {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, {
            it.data?.run { showMessage(this) }
        })
    }
}