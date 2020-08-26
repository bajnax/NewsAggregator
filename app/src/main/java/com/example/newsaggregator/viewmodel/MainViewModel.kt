package com.example.newsaggregator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.example.newsaggregator.model.NewsRepository
import com.example.newsaggregator.model.data.Article
import com.example.newsaggregator.model.data.Channel
import com.example.newsaggregator.model.db.NewsDatabaseHelperImpl
import com.example.newsaggregator.model.paging.ArticlesBoundaryCallback
import com.example.newsaggregator.model.paging.ChannelsBoundaryCallback
import com.example.newsaggregator.model.remote.RemoteDataModel
import com.example.newsaggregator.model.wrappers.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModelModule = module {
    viewModel { MainViewModel(get(), get(), get()) }
}

class MainViewModel(
    private val newsRepository: NewsRepository,
    private val remoteDataModel: RemoteDataModel,
    private val databaseHelper: NewsDatabaseHelperImpl
) : ViewModel() {

    private val _index = MutableLiveData<Int>()

    fun setIndex(index: Int) {
        _index.value = index
    }

    // article
    lateinit var pagedArticles: LiveData<PagedList<Article>>
    lateinit var articlesBoundaryCallback: ArticlesBoundaryCallback

    // channel
    lateinit var pagedChannels: LiveData<PagedList<Channel>>
    lateinit var channelsBoundaryCallback: ChannelsBoundaryCallback

    // common
    lateinit var requestState: MutableLiveData<Status>
    lateinit var networkErrors: MutableLiveData<String>

    fun fetchArticles(searchPhrase: String = "") {
        articlesBoundaryCallback =
            ArticlesBoundaryCallback(
                viewModelScope,
                remoteDataModel,
                databaseHelper,
                searchPhrase
            )
        val pagedArticlesResult =
            newsRepository.fetchArticlesBySearchPhrase(
                viewModelScope,
                "",
                0,
                articlesBoundaryCallback
            )

        pagedArticles = pagedArticlesResult.pagedArticles
        requestState = pagedArticlesResult.requestState
        networkErrors = pagedArticlesResult.networkErrors
    }

    fun fetchChannels() {
        channelsBoundaryCallback =
            ChannelsBoundaryCallback(
                viewModelScope,
                remoteDataModel,
                databaseHelper
            )
        val pagedChannelsResult =
            newsRepository.fetchChannels(
                viewModelScope,
                0,
                channelsBoundaryCallback
            )

        pagedChannels = pagedChannelsResult.pagedChannels
        requestState = pagedChannelsResult.requestState
        networkErrors = pagedChannelsResult.networkErrors
    }

    fun updateChannelFavoriteState(isFavorite: Boolean, id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.updateChannelFavoriteState(this, isFavorite, id)
        }
    }

}