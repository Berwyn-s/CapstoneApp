package com.example.capstoneapp.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.capstoneapp.R
import com.example.capstoneapp.databinding.ActivityDetailBinding
import com.example.capstoneapp.databinding.ContentDetailEntertainmentBinding
import com.example.core.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding

    private var _contentBinding: ContentDetailEntertainmentBinding? = null
    private val contentBinding get() = _contentBinding

    private val viewModel: DetailActivityViewModel by viewModels()
    private lateinit var movie : Movie


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        _contentBinding = binding?.detailContent
        setContentView(binding?.root)

        val extras = intent.extras

        extras?.let {
            extras.getParcelable<Movie>(EXTRA_ENTERTAINMENT)?.let{
                movie = it
                loadItemMovie()
                editFavoriteBtn(movie.favorite)
                turnOffProgressBar()
            }
        }

        val toolbar = binding?.toolbar
        setSupportActionBar(toolbar)
        binding?.collapsingToolbarLayout?.title = " "

        contentBinding?.FavoriteBtn?.setOnClickListener {
            val message = if (movie.favorite) "Removed from favorite" else "Added to favorite";
            viewModel.setMovieFavorite(movie, !movie.favorite)
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT ).show()
            movie.favorite = !movie.favorite
            editFavoriteBtn(movie.favorite)
        }
    }

    private fun loadItemMovie() {
        binding?.ivDetailHeader?.let {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/" + movie.imageHeaderPath)
                .into(it)
        }

        binding?.ivDetailPicture?.let {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/" + movie.imagePath)
                .transform(RoundedCorners(20))
                .into(it)
        }

        binding?.tvTitle?.text = movie.title

        contentBinding?.apply {
            tvRating.text = movie.rating
            tvDate.text = movie.date
            tvDescription.text = movie.overview
        }

        binding?.detailContent?.shareBtn?.visibility = View.VISIBLE
        binding?.detailContent?.shareBtn?.setOnClickListener {
            val userSummary =
                getString(R.string.lets_watch_this_movie, movie.title)
            val intent = Intent(Intent.ACTION_SEND).setType("text/plain")
                .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject))
                .putExtra(Intent.EXTRA_TEXT, userSummary)

            startActivity(intent)
        }
    }

    private fun editFavoriteBtn(isFavorite : Boolean){
        if (isFavorite){
            contentBinding?.FavoriteBtn?.text = "Remove Favorite"
        }else{
            contentBinding?.FavoriteBtn?.text = "Add Favorite"
        }
    }

    private fun turnOffProgressBar(){
        if (binding?.progressBar?.visibility == View.VISIBLE){
            binding?.progressBar?.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _contentBinding = null
        _binding = null
    }

    companion object {
        const val EXTRA_ENTERTAINMENT = "extra_entertainment"
    }
}