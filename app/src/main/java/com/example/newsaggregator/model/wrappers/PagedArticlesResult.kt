package com.example.newsaggregator.model.wrappers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.newsaggregator.model.data.Article

data class PagedArticlesResult(
    val pagedArticles: LiveData<PagedList<Article>>,
    val requestState: MutableLiveData<Status>,
    val networkErrors: MutableLiveData<String>
)