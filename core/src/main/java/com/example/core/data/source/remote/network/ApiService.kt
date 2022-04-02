package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.MoviesResponse
import com.example.core.BuildConfig.API_TOKEN
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMoviesList(
        @Query("api_key") apiKey: String = API_TOKEN
    ): MoviesResponse

}