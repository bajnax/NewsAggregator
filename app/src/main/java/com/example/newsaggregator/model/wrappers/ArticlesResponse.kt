package com.example.newsaggregator.model.wrappers

import com.example.newsaggregator.model.data.Article

data class ArticlesResponse(
    val status: String,
    val totalResults: Long,
    val articles: List<Article>,
    val message: String?
)