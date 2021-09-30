package com.hashimshafiq.moviedemo.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.hashimshafiq.moviedemo.R
import com.hashimshafiq.moviedemo.data.local.db.entity.Movie

class MoviesAdapter(
    private val dataList : ArrayList<Movie>,
    private val onClick:(Movie,ImageView) -> Unit
) : RecyclerView.Adapter<MovieItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_movie_row, parent, false)

        return MovieItemViewHolder(view,onClick)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    fun appendData(dataList: List<Movie>) {
        val oldCount = itemCount
        this.dataList.addAll(dataList)
        val currentCount = itemCount
        if (oldCount == 0 && currentCount > 0)
            notifyDataSetChanged()
        else if (oldCount in 1 until currentCount)
            notifyItemRangeChanged(oldCount - 1, currentCount - oldCount)
    }

    fun updateData(dataList: List<Movie>){
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

}