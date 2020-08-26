package com.example.newsaggregator.model.data.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsaggregator.model.data.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getPagedArticlesTest(): DataSource.Factory<Int, Article>

    @Query("SELECT * FROM article WHERE title LIKE :searchPhrase")
    fun getPagedArticlesBySearchPhrase(searchPhrase: String): DataSource.Factory<Int, Article>

    // todo: adjust request to fetch articles by channelId
    @Query("SELECT * FROM article WHERE title LIKE :channelId")
    fun getPagedArticlesByChannelId(channelId: String): DataSource.Factory<Int, Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<Article>)

}