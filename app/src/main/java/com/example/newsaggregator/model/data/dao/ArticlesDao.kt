package com.example.newsaggregator.model.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.newsaggregator.model.data.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    suspend fun getAllArticles(): List<Article>

    @Insert
    suspend fun insertArticles(articles: List<Article>)

    @Delete
    suspend fun deleteArticle(article: Article)

}