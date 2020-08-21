package com.example.newsaggregator.model.paging

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.newsaggregator.app.Constants.DEFAULT_PAGE_SIZE
import com.example.newsaggregator.model.remote.RemoteDataModel
import com.example.newsaggregator.model.wrappers.Status
import com.example.newsaggregator.model.data.Article
import com.example.newsaggregator.model.db.NewsDatabaseHelperImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ArticlesBoundaryCallback(
    val coroutineScope: CoroutineScope,
    private val remoteDataModel: RemoteDataModel,
    private val databaseHelper: NewsDatabaseHelperImpl,
    var searchPhrase: String = ""
) : PagedList.BoundaryCallback<Article>() {

    val networkError = MutableLiveData<String>()
    val requestState = MutableLiveData<Status>()

    private var isRequesting = false
    var lastPage = 0

    @MainThread
    override fun onZeroItemsLoaded() {
        downloadMoreArticles()
    }

    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: Article) {
        downloadMoreArticles()
    }

    private suspend fun updateLocalArticles(articles: List<Article>) {
        databaseHelper.insertArticles(articles)
    }

    private fun downloadMoreArticles() {
        Log.d("Page: ", lastPage.toString())
        if (isRequesting) {
            return
        }
        coroutineScope.launch {
            try {
                requestState.postValue(Status.LOADING)

                val response =
                    remoteDataModel.getPagedArticles(DEFAULT_PAGE_SIZE, lastPage, searchPhrase)
                with(response) {
                    message?.let {
                        requestState.postValue(Status.ERROR)
                        networkError.postValue(it)
                    } ?: run {
                        requestState.postValue(Status.SUCCESS)
                        body?.let {
                            updateLocalArticles(it.articles)
                            lastPage++
                        }
                    }
                }
            } catch (e: Exception) {
                requestState.postValue(Status.ERROR)
                networkError.postValue(e.message ?: e.localizedMessage)
                Log.d("Articles fetched with exception: ", e.message ?: e.localizedMessage)
            }
        }
    }
}