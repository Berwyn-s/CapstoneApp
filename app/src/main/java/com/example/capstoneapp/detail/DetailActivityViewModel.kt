package com.example.capstoneapp.detail

import androidx.lifecycle.ViewModel
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailActivityViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun setMovieFavorite(movie: Movie , status: Boolean){
        movieUseCase.setFavoriteMovie(movie, status)
    }
}