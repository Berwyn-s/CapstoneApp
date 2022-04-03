package com.example.capstoneapp.favorites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.capstoneapp.detail.DetailActivity
import com.example.capstoneapp.di.FavoriteModuleDependencies
import com.example.capstoneapp.favorites.databinding.ActivityFavoriteBinding
import com.example.core.domain.model.Movie
import com.example.core.ui.MovieAdapter
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }

    private val mAdapter = MovieAdapter()
    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    this,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Favorite Movies"

        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(context, 2)
            this.adapter = mAdapter
        }
        getFavoriteMovies()
    }

    private fun getFavoriteMovies(){
        viewModel.favoriteMovies.observe(this){
            progressBarEnabled(false)
            mAdapter.setData(it as ArrayList<Movie>?)
            Toast.makeText(this@FavoriteActivity, "Favorite Movies", Toast.LENGTH_LONG).show()
            setAdapterCallback()
        }
    }

    private fun setAdapterCallback(){
        mAdapter.setCallback(object : MovieAdapter.MovieCallback{
            override fun onMovieClicked(movie: Movie) {
                Toast.makeText(this@FavoriteActivity, movie.title, Toast.LENGTH_LONG).show()
                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ENTERTAINMENT, movie)
                startActivity(intent)
            }
        })
    }

    private fun progressBarEnabled(state : Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}