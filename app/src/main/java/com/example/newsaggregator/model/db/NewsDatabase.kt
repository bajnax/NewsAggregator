package com.example.newsaggregator.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsaggregator.model.data.Article
import com.example.newsaggregator.model.data.Channel
import com.example.newsaggregator.model.data.dao.ArticleDao
import com.example.newsaggregator.model.data.dao.ChannelDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val newsDatabaseModule = module {
    single {
        NewsDatabase.getInstance(
            androidApplication()
        )
    }
}

@Database(entities = [Article::class, Channel::class], version = 5, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    abstract fun channelDao(): ChannelDao

    companion object DatabaseBuilder {

        @Volatile
        private var NEWS_DB_INSTANCE: NewsDatabase? = null

        fun getInstance(context: Context): NewsDatabase = NEWS_DB_INSTANCE
            ?: synchronized(this) {
                NEWS_DB_INSTANCE
                    ?: buildNewsDB(
                        context
                    ).also {
                    NEWS_DB_INSTANCE = it
                }
            }

        private fun buildNewsDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "news-database"
            ).fallbackToDestructiveMigration().build()

    }

}

