package com.example.newsaggregator.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Channel(
    @PrimaryKey
    val id: String,
    val name: String?,
    val description: String?,
    val url: String?,
    var isFavorite: Boolean = false
)