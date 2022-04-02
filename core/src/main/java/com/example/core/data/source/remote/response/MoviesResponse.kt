package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    @field:SerializedName("results")
    val results: List<ResultsMovies>
)


