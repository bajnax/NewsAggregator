package com.example.newsaggregator.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Article(
    @PrimaryKey val id: Int,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
) {

}