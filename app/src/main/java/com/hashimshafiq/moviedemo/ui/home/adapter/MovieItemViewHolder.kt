package com.hashimshafiq.moviedemo.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hashimshafiq.moviedemo.R
import com.hashimshafiq.moviedemo.data.local.db.entity.Movie
import com.hashimshafiq.moviedemo.databinding.CustomMovieRowBinding
import com.hashimshafiq.moviedemo.di.components.ViewHolderComponent
import com.hashimshafiq.moviedemo.ui.base.BaseItemViewHolder

class MovieItemViewHolder(parent : ViewGroup, private val onClick: (Movie,ImageView) -> Unit) : BaseItemViewHolder<Movie, MovieItemViewModel>(R.layout.custom_movie_row,parent) {




    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupView(view: View) {
        val binding : CustomMovieRowBinding =
            CustomMovieRowBinding.bind(view)

        viewModel.movieName.observe(this, { name ->
            name?.run {
                binding.movieName.text = name
            }
        })

        viewModel.movieAverageVote.observe(this, { averageVote ->
            averageVote?.run {
                binding.movieRating.text = itemView.context.getString(R.string.averageVote,averageVote.toString())
            }

        })

        viewModel.movieOverview.observe(this, { overview ->
            overview?.run {
                binding.movieOverview.text = overview
            }

        })

        viewModel.releaseDate.observe(this, { releaseDate ->

            releaseDate?.run {
                binding.movieReleaseDate.text = itemView.context.getString(R.string.releaseDate,releaseDate)
            }

        })



        viewModel.imageDetail.observe(this, {
            it?.run {
                val glideRequest = Glide
                        .with(binding.movieImage.context)
                        .load(this)



                glideRequest.into(binding.movieImage)
            }
        })

        itemView.setOnClickListener {
            //onItemClickListener.onItemClicked(viewModel.data.value!!,itemView.movieImage)
            onClick(viewModel.data.value!!,binding.movieImage)
        }


    }




}