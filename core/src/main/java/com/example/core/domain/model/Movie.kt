package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(

    val entertainmentId: Int,

    val title: String? = null,

    val imagePath: String? = null,

    val imageHeaderPath: String? = null,

    val overview: String? = null,

    val date: String? = null,

    val rating: String? = null,

    var favorite: Boolean = false
) : Parcelable
