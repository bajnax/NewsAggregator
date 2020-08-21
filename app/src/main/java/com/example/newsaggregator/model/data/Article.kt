package com.example.newsaggregator.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Article(
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    @PrimaryKey
    val publishedAt: String,
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String? = ""
)