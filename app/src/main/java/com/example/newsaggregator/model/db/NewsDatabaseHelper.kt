package com.example.newsaggregator.model.db

import androidx.paging.DataSource
import com.example.newsaggregator.model.data.Article
import com.example.newsaggregator.model.data.Channel

interface NewsDatabaseHelper {

    // articles
    suspend fun getPagedArticlesBySearchPhrase(searchPhrase: String): DataSource.Factory<Int, Article>

    suspend fun getPagedArticlesByChannelId(channelId: String): DataSource.Factory<Int, Article>

    suspend fun insertArticles(articles: List<Article>)


    // channels
    suspend fun getPagedChannels(): DataSource.Factory<Int, Channel>

    suspend fun getPagedFavoriteChannels(isFavorite: Boolean): DataSource.Factory<Int, Channel>

    suspend fun updateChannelFavoriteState(isFavorite: Boolean, id: String)

    suspend fun insertChannels(channels: List<Channel>)

}