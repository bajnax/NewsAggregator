package com.example.newsaggregator.model

import com.example.newsaggregator.model.data.Article
import retrofit2.http.GET

interface NewsApi {
    @GET("")
    suspend fun getAllArticles(): List<Article>
}