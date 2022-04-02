package com.example.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.databinding.ItemsEntertainmentBinding
import com.example.core.domain.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.EntertainsViewHolder>() {

    private lateinit var movieCallback: MovieCallback
    private var listMovie = ArrayList<Movie>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListMovie : ArrayList<Movie>?){
        if (newListMovie == null)
            return
        listMovie.clear()
        listMovie.addAll(newListMovie)
        notifyDataSetChanged()
    }

    fun setCallback( movieCallback : MovieCallback){
        this.movieCallback = movieCallback
    }

    inner class EntertainsViewHolder(private val binding: ItemsEntertainmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            with(binding) {
                tvTitle.text = item.title
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/" + item.imagePath)
                    .into(ivEntertainmentPicture)

                itemView.setOnClickListener {
                    movieCallback.onMovieClicked(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntertainsViewHolder {
        val itemsEntertainmentBindings =
            ItemsEntertainmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EntertainsViewHolder(itemsEntertainmentBindings)
    }

    override fun onBindViewHolder(holder: EntertainsViewHolder, position: Int) {
        val item = listMovie[position]
        holder.bind(item)
    }

    override fun getItemCount() = listMovie.size

    interface MovieCallback{
        fun onMovieClicked(movie : Movie)
    }

}