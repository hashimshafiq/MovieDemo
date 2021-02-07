package com.hashimshafiq.moviedemo.ui.home.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import com.hashimshafiq.moviedemo.data.local.db.entity.Movie
import com.hashimshafiq.moviedemo.ui.base.BaseAdapter

class MoviesAdapter(
    parentLifecycle: Lifecycle,
    movies : ArrayList<Movie>,
    private val onClick:(Movie,ImageView) -> Unit
) : BaseAdapter<Movie, MovieItemViewHolder>(parentLifecycle,movies) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder = MovieItemViewHolder(parent,onClick)

}