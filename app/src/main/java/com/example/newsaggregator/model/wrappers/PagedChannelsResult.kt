package com.example.newsaggregator.model.wrappers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.newsaggregator.model.data.Channel

data class PagedChannelsResult(
    val pagedChannels: LiveData<PagedList<Channel>>,
    val requestState: MutableLiveData<Status>,
    val networkErrors: MutableLiveData<String>
)