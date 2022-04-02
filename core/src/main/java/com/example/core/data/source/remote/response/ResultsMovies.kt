package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResultsMovies(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("original_title")
    val originalTitle: String? = null,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("release_date")
    val date: String,

    @field:SerializedName("vote_average")
    val rating: Double
)
