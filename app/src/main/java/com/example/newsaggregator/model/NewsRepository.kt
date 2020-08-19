package com.example.newsaggregator.model

import com.example.newsaggregator.model.db.NewsDatabaseHelperImpl
import org.koin.dsl.module

val centralNewsRepository =
    module {
        single { NewsRepository(get(), get(), get()) }
    }

class NewsRepository(private val newsApi: NewsApi, private val databaseHelper: NewsDatabaseHelperImpl, private val connectivity: ConnectivityModule) {
    suspend fun getAllArticles() = if (connectivity.isOnline) newsApi.getAllArticles() else databaseHelper.getAllArticles()
}