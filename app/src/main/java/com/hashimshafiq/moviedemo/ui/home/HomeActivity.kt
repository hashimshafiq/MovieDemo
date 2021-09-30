package com.hashimshafiq.moviedemo.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hashimshafiq.moviedemo.R
import com.hashimshafiq.moviedemo.data.local.db.entity.Movie
import com.hashimshafiq.moviedemo.databinding.ActivityMainBinding
//import com.hashimshafiq.moviedemo.di.components.ActivityComponent
import com.hashimshafiq.moviedemo.ui.detail.MovieDetailActivity
import com.hashimshafiq.moviedemo.ui.home.adapter.MoviesAdapter
import com.hashimshafiq.moviedemo.utils.common.Status
import com.hashimshafiq.moviedemo.utils.display.Toaster
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    companion object{
        const val TAG = "HomeActivity"
    }



    private lateinit var binding : ActivityMainBinding

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var moviesAdapter: MoviesAdapter

    val viewModel : HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()
        setupView()
        viewModel.onCreate()
    }



    private fun showMessage(message: String) = Toaster.show(applicationContext, message)

    private fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    private fun setupView() {

        moviesAdapter = MoviesAdapter(arrayListOf()){ movie, imageView ->

            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra(MovieDetailActivity.MOVIE_DATA, movie)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                imageView,
                imageView.transitionName
            )
            startActivity(intent, options.toBundle())
        }

        binding.listView.apply {
            layoutManager = linearLayoutManager
            adapter = moviesAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    layoutManager?.run {
                        if(this is LinearLayoutManager
                                && itemCount>1
                                && itemCount == findLastVisibleItemPosition()+1){

                            viewModel.loadMore()
                        }
                    }
                }
            })

        }

    }

    private fun setupObservers() {

        viewModel.messageString.observe(this, {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, {
            it.data?.run { showMessage(this) }
        })

        viewModel.getMoviesList().observe(this, {
            when(it.status){
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { movieList -> renderList(movieList.movies!!) }
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    showMessage(it.data.toString())
                }
                Status.UNKNOWN -> {
                    showMessage(R.string.network_default_error)
                }

            }
        })
    }

    private fun renderList(newsList: List<Movie>) {
        moviesAdapter.appendData(newsList)
    }

}