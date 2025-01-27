package com.example.newsaggregator.model.remote

import com.example.newsaggregator.model.NewsApi
import com.example.newsaggregator.model.helper.ResponseHandler
import org.koin.dsl.module

val remoteDataModel = module {
    factory { RemoteDataModel(get()) }
}

class RemoteDataModel(private val newsApi: NewsApi): ResponseHandler() {

    // todo: remove in the future. Use for testing atm
    suspend fun getPagedArticles(pageSize: Int, page: Int, searchPhrase: String?) = getResponse {
        newsApi.getPagedArticlesBySearchPhrase(pageSize, page+1) //api does not allow to use 0 page, so it starts from 1  :/
    }

    suspend fun getPagedArticlesBySearchPhrase(pageSize: Int, page: Int, searchPhrase: String?) = getResponse {
        newsApi.getPagedArticlesBySearchPhrase(pageSize, page, searchPhrase)
    }

    suspend fun getPagedArticlesByChannels(pageSize: Int, page: Int, channelIdsList: List<String>) = getResponse {
        newsApi.getPagedArticlesByChannels(pageSize, page, channelIdsList.filter { it.trim().isNullOrBlank() }.joinToString { "," })
    }

    suspend fun getPagedChannels(pageSize: Int, page: Int) = getResponse {
        newsApi.getPagedChannels(pageSize, page)
    }

}