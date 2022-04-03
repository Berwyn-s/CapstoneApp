package com.example.core.utils


import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.remote.response.MoviesResponse
import com.example.core.data.source.remote.response.ResultsMovies
import com.example.core.domain.model.Movie


object DataMapper {

//    From Retrofit Framework to Room
    fun mapResponsesToEntities(input: List<MoviesResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input[0].results.map {
            val movie = MovieEntity(
                entertainmentId = it.id,
                overview = it.overview,
                title = it.originalTitle,
                imagePath = it.posterPath,
                imageHeaderPath = it.backdropPath,
                date = it.date,
                rating = it.rating.toString(),
                favorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

//    From room framework to domain data
    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                entertainmentId = it.entertainmentId,
                overview = it.overview,
                title = it.title,
                imagePath = it.imagePath,
                imageHeaderPath = it.imageHeaderPath,
                date = it.date,
                rating = it.rating,
                favorite = it.favorite
            )
        }

//    From domain data to room framework
    fun mapDomainToEntity(input: Movie) = MovieEntity(
        entertainmentId = input.entertainmentId,
        imagePath = input.imagePath,
        imageHeaderPath = input.imageHeaderPath,
        title = input.title,
        overview = input.overview ?: "No overview",
        date = input.date ?: "19-03-2022",
        rating = input.rating
    )
}


