package com.example.core.data.source.local

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    suspend fun insertMovies(entities : List<MovieEntity>) = movieDao.insertMovies(entities)

    fun getMovies() : Flow<List<MovieEntity>> = movieDao.getMovies()

    fun getMoviesWithId(entertainmentId : Int) : Flow<MovieEntity> = movieDao.getIdMovies(entertainmentId)

    fun getFavoriteMovies() : Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

     fun setMovieFavorite(entity: MovieEntity, isFavorite : Boolean) {
        entity.favorite = isFavorite
        movieDao.updateMovies(entity)
    }

}