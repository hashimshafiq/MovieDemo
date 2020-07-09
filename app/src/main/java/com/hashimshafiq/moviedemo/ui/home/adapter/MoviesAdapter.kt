package com.hashimshafiq.moviedemo.ui.home.adapter

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.hashimshafiq.moviedemo.data.local.db.entity.Movie
import com.hashimshafiq.moviedemo.ui.base.BaseAdapter

class MoviesAdapter(parentLifecycle: Lifecycle,movies : ArrayList<Movie>)
    : BaseAdapter<Movie, MovieItemViewHolder>(parentLifecycle,movies) {

    private lateinit var onItemClickListener: OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder = MovieItemViewHolder(parent,onItemClickListener)

    fun attachListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }
}