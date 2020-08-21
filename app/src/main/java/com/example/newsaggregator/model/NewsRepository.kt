package com.example.newsaggregator.model

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.newsaggregator.app.Constants.DEFAULT_PAGE_SIZE
import com.example.newsaggregator.model.data.Article
import com.example.newsaggregator.model.data.Channel
import com.example.newsaggregator.model.db.NewsDatabaseHelperImpl
import com.example.newsaggregator.model.remote.ConnectivityModule
import com.example.newsaggregator.model.paging.ArticlesBoundaryCallback
import com.example.newsaggregator.model.paging.ChannelsBoundaryCallback
import com.example.newsaggregator.model.wrappers.PagedArticlesResult
import com.example.newsaggregator.model.wrappers.PagedChannelsResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.dsl.module

val centralNewsRepository =
    module {
        single { NewsRepository(get(), get()) }
    }

class NewsRepository(
    private val databaseHelper: NewsDatabaseHelperImpl,
    private val connectivityModule: ConnectivityModule
) {
    companion object {

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(DEFAULT_PAGE_SIZE)
            .setPageSize(DEFAULT_PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }


    fun fetchArticlesBySearchPhrase(coroutineScope: CoroutineScope, searchPhrase: String, lastRequestedPage: Int, articlesBoundaryCallback: ArticlesBoundaryCallback): PagedArticlesResult {
        lateinit var dataSourceLocalFactory: DataSource.Factory<Int, Article>
        lateinit var data : LivePagedListBuilder<Int, Article>
        coroutineScope.launch {
            try {
                dataSourceLocalFactory = databaseHelper.getPagedArticlesBySearchPhrase(searchPhrase)
            } catch (e : Exception) {
                Log.e("DB error: ", e.message ?: e.localizedMessage)
            }
        }

        articlesBoundaryCallback.searchPhrase = searchPhrase
        articlesBoundaryCallback.lastPage = lastRequestedPage
        val networkError = articlesBoundaryCallback.networkError
        val requestState = articlesBoundaryCallback.requestState

        data = if (connectivityModule.isOnline) {
            LivePagedListBuilder(dataSourceLocalFactory, pagedListConfig()).setBoundaryCallback(articlesBoundaryCallback)
        } else {
            LivePagedListBuilder(dataSourceLocalFactory, pagedListConfig())
        }
        return PagedArticlesResult(
            data.build(),
            requestState,
            networkError
        )
    }

    fun fetchChannels(coroutineScope: CoroutineScope, lastRequestedPage: Int, channelsBoundaryCallback: ChannelsBoundaryCallback): PagedChannelsResult {
        lateinit var dataSourceLocalFactory: DataSource.Factory<Int, Channel>
        lateinit var data : LivePagedListBuilder<Int, Channel>
        coroutineScope.launch {
            try {
                dataSourceLocalFactory = databaseHelper.getPagedChannels()
            } catch (e : Exception) {
                Log.e("DB error: ", e.message ?: e.localizedMessage)
            }
        }

        channelsBoundaryCallback.lastPage = lastRequestedPage
        val networkError = channelsBoundaryCallback.networkError
        val requestState = channelsBoundaryCallback.requestState

        data = if (connectivityModule.isOnline) {
            LivePagedListBuilder(dataSourceLocalFactory, pagedListConfig()).setBoundaryCallback(channelsBoundaryCallback)
        } else {
            LivePagedListBuilder(dataSourceLocalFactory, pagedListConfig())
        }
        return PagedChannelsResult(
            data.build(),
            requestState,
            networkError
        )
    }


    fun fetchFavoriteChannels(coroutineScope: CoroutineScope, lastRequestedPage: Int, channelsBoundaryCallback: ChannelsBoundaryCallback): PagedChannelsResult {
        lateinit var dataSourceLocalFactory: DataSource.Factory<Int, Channel>
        lateinit var data : LivePagedListBuilder<Int, Channel>
        coroutineScope.launch {
            try {
                dataSourceLocalFactory = databaseHelper.getPagedFavoriteChannels(true)
            } catch (e : Exception) {
                Log.e("DB error: ", e.message ?: e.localizedMessage)
            }
        }

        channelsBoundaryCallback.lastPage = lastRequestedPage
        val networkError = channelsBoundaryCallback.networkError
        val requestState = channelsBoundaryCallback.requestState

        LivePagedListBuilder(dataSourceLocalFactory, pagedListConfig())

        return PagedChannelsResult(
            data.build(),
            requestState,
            networkError
        )
    }

}