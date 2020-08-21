package com.example.newsaggregator.model

import com.example.newsaggregator.model.wrappers.ArticlesResponse
import com.example.newsaggregator.model.wrappers.ChannelsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    // todo: remove in the future
    @GET("top-headlines")
    suspend fun getPagedArticlesTest(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("country") country: String = "ru"
    ): Response<ArticlesResponse>




    @GET("everything")
    suspend fun getPagedArticlesBySearchPhrase(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("q") searchPhrase: String? = ""
    ): Response<ArticlesResponse>

    @GET("everything")
    suspend fun getPagedArticlesByChannels(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("sources") sourcesFilter: String? = ""
    ): Response<ArticlesResponse>

    @GET("sources")
    suspend fun getPagedChannels(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): Response<ChannelsResponse>
}