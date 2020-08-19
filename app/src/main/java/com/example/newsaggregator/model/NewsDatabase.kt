package com.example.newsaggregator.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsaggregator.model.data.Article
import com.example.newsaggregator.model.data.dao.ArticleDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val newsDatabaseModule = module {
    single { DatabaseBuilder.getInstance(androidApplication()) }
}

@Database(entities = [Article::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

}

object DatabaseBuilder {

    private var NEWS_DB_INSTANCE: NewsDatabase? = null

    fun getInstance(context: Context): NewsDatabase {
        if (NEWS_DB_INSTANCE == null) {
            synchronized(NewsDatabase::class) {
                NEWS_DB_INSTANCE = buildNewsDB(context)
            }
        }
        return NEWS_DB_INSTANCE!!
    }

    private fun buildNewsDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java,
            "news-database"
        ).build()

}