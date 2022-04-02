package com.example.core.data.source.local.room

import androidx.room.*
import com.example.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentities")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentities WHERE entertainmentId = :id")
    fun getIdMovies(id: Int): Flow<MovieEntity>

    @Query("SELECT * FROM movieentities WHERE favorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertMovies(movieEntities: List<MovieEntity>)

    @Update
     fun updateMovies(movieEntities: MovieEntity)

}