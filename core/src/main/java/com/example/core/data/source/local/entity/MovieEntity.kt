package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "entertainmentId")
    val entertainmentId: Int,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "imagePath")
    val imagePath: String? = null,

    @ColumnInfo(name = "imageHeaderPath")
    val imageHeaderPath: String? = null,

    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "date")
    val date: String? = null,

    @ColumnInfo(name = "rating")
    val rating: String? = null,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)
