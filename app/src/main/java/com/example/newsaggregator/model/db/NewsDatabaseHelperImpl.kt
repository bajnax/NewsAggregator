package com.example.newsaggregator.model.db

import com.example.newsaggregator.model.NewsDatabase
import com.example.newsaggregator.model.data.Article
import org.koin.dsl.module

val newsDatabaseHelper = module {
    factory { provideDatabaseHelper(get()) }
}

fun provideDatabaseHelper(newsDatabase: NewsDatabase) = NewsDatabaseHelperImpl(newsDatabase)

class NewsDatabaseHelperImpl(private val newsDatabase: NewsDatabase) : NewsDatabaseHelper {

    override suspend fun getAllArticles(): List<Article> = newsDatabase.articleDao().getAllArticles()

    override suspend fun insertArticles(articles: List<Article>) = newsDatabase.articleDao().insertArticles(articles)

    override suspend fun deleteArticle(article: Article) = newsDatabase.articleDao().deleteArticle(article)

}