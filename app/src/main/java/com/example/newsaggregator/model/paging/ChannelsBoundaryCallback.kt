package com.example.newsaggregator.model.paging

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.newsaggregator.app.Constants
import com.example.newsaggregator.model.remote.RemoteDataModel
import com.example.newsaggregator.model.wrappers.Status
import com.example.newsaggregator.model.data.Channel
import com.example.newsaggregator.model.db.NewsDatabaseHelperImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ChannelsBoundaryCallback(
    val coroutineScope: CoroutineScope,
    private val remoteDataModel: RemoteDataModel,
    private val databaseHelper: NewsDatabaseHelperImpl
) : PagedList.BoundaryCallback<Channel>() {

    val networkError = MutableLiveData<String>()
    val requestState = MutableLiveData<Status>()

    private var isRequesting = false
    var lastPage = 0

    @MainThread
    override fun onZeroItemsLoaded() {
        downloadMoreChannels()
    }

    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: Channel) {
        downloadMoreChannels()
    }

    private suspend fun updateLocalChannels(articles: List<Channel>) {
        databaseHelper.insertChannels(articles)
    }

    private fun downloadMoreChannels() {
        Log.d("Page: ", lastPage.toString())
        if (isRequesting) {
            return
        }
        coroutineScope.launch {
            try {
                requestState.postValue(Status.LOADING)

                val response =
                    remoteDataModel.getPagedChannels(Constants.DEFAULT_PAGE_SIZE, lastPage)
                with(response) {
                    message?.let {
                        requestState.postValue(Status.ERROR)
                        networkError.postValue(it)
                    } ?: run {
                        requestState.postValue(Status.SUCCESS)
                        body?.let {
                            updateLocalChannels(it.channels)
                            lastPage++
                        }
                    }
                }
            } catch (e: Exception) {
                requestState.postValue(Status.ERROR)
                networkError.postValue(e.message ?: e.localizedMessage)
                Log.d("Channels fetched with exception: ", e.message ?: e.localizedMessage)
            }
        }
    }
}