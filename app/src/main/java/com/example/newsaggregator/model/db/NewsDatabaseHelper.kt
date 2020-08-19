package com.example.newsaggregator.model.db

import com.example.newsaggregator.model.data.Article

interface NewsDatabaseHelper {

    suspend fun getAllArticles(): List<Article>

    suspend fun insertArticles(articles: List<Article>)

    suspend fun deleteArticle(article: Article)

}