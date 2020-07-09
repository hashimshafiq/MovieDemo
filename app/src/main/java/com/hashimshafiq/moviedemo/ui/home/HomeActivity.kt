package com.hashimshafiq.moviedemo.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hashimshafiq.moviedemo.R
import com.hashimshafiq.moviedemo.data.local.db.entity.Movie
import com.hashimshafiq.moviedemo.di.components.ActivityComponent
import com.hashimshafiq.moviedemo.ui.base.BaseActivity
import com.hashimshafiq.moviedemo.ui.detail.MovieDetailActivity
import com.hashimshafiq.moviedemo.ui.home.adapter.MoviesAdapter
import com.hashimshafiq.moviedemo.ui.home.adapter.OnItemClickListener
import com.hashimshafiq.moviedemo.utils.common.Status
import com.hashimshafiq.moviedemo.utils.display.Toaster
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity : BaseActivity<HomeViewModel>(), OnItemClickListener {

    companion object{
        const val TAG = "HomeActivity"
    }

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) = activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        moviesAdapter.attachListener(this)

        listView.apply {
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

    override fun setupObservers() {
        super.setupObservers()

        viewModel.getMoviesList().observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { movieList -> renderList(movieList.movies!!) }
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    Toaster.show(this, getString(R.string.network_connection_error))
                }
                Status.UNKNOWN -> {
                    Toaster.show(this, getString(R.string.network_default_error))
                }

            }
        })
    }

    private fun renderList(newsList: List<Movie>) {
        moviesAdapter.appendData(newsList)
    }

    override fun onItemClicked(movie: Movie, imageView: ImageView) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE_DATA, movie)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                imageView,
                imageView.transitionName
        )

        startActivity(intent, options.toBundle())
    }
}