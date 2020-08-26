package com.example.newsaggregator.model.db

import com.example.newsaggregator.model.data.Article
import com.example.newsaggregator.model.data.Channel
import org.koin.dsl.module

val newsDatabaseHelper = module {
    factory { provideDatabaseHelper(get()) }
}

fun provideDatabaseHelper(newsDatabase: NewsDatabase) = NewsDatabaseHelperImpl(newsDatabase)

class NewsDatabaseHelperImpl(private val newsDatabase: NewsDatabase) : NewsDatabaseHelper {

    // articles
    override suspend fun getPagedArticlesBySearchPhrase(searchPhrase: String) =
        newsDatabase.articleDao().getPagedArticlesTest()
        //newsDatabase.articleDao().getPagedArticlesBySearchPhrase(searchPhrase)

    override suspend fun getPagedArticlesByChannelId(channelId: String) =
        newsDatabase.articleDao().getPagedArticlesByChannelId(channelId)

    override suspend fun insertArticles(articles: List<Article>) =
        newsDatabase.articleDao().insertArticles(articles)

    // channels
    override suspend fun getPagedChannels() = newsDatabase.channelDao().getPagedChannels()

    override suspend fun getPagedFavoriteChannels(isFavorite: Boolean) =
        newsDatabase.channelDao().getPagedFavoriteChannels(if (isFavorite) 1 else 0)

    override suspend fun updateChannelFavoriteState(isFavorite: Boolean, id: String) {
        newsDatabase.channelDao().updateChannelFavoriteState(if (isFavorite) 1 else 0, id)
    }

    override suspend fun insertChannels(channels: List<Channel>) =
        newsDatabase.channelDao().insertChannels(channels)

}