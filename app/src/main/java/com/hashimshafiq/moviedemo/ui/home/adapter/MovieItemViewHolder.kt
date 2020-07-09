package com.hashimshafiq.moviedemo.ui.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.hashimshafiq.moviedemo.R
import com.hashimshafiq.moviedemo.data.local.db.entity.Movie
import com.hashimshafiq.moviedemo.di.components.ViewHolderComponent
import com.hashimshafiq.moviedemo.ui.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.custom_movie_row.view.*

class MovieItemViewHolder(parent : ViewGroup, private val onItemClickListener: OnItemClickListener) : BaseItemViewHolder<Movie, MovieItemViewModel>(R.layout.custom_movie_row,parent) {


    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }


    override fun setupView(view: View) {

        viewModel.movieName.observe(this, Observer { name ->
            name?.run {
                itemView.movieName.text = name
            }
        })

        viewModel.movieAverageVote.observe(this, Observer {averageVote ->
            averageVote?.run {
                itemView.movieRating.text = itemView.context.getString(R.string.averageVote,averageVote.toString())
            }

        })

        viewModel.movieOverview.observe(this, Observer {overview ->
            overview?.run {
                itemView.movieOverview.text = overview
            }

        })

        viewModel.releaseDate.observe(this, Observer {releaseDate ->

            releaseDate?.run {
                itemView.movieReleaseDate.text = itemView.context.getString(R.string.releaseDate,releaseDate)
            }

        })



        viewModel.imageDetail.observe(this, Observer {
            it?.run {
                val glideRequest = Glide
                        .with(itemView.movieImage.context)
                        .load(this)



                glideRequest.into(itemView.movieImage)
            }
        })

        itemView.setOnClickListener {
            onItemClickListener.onItemClicked(viewModel.data.value!!,itemView.movieImage)
        }


    }




}