package com.example.newsaggregator.model.data.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsaggregator.model.data.Channel

@Dao
interface ChannelDao {

    @Query("SELECT * FROM channel")
    fun getPagedChannels(): DataSource.Factory<Int, Channel>

    @Query("SELECT * FROM channel WHERE isFavorite = :isFavorite")
    fun getPagedFavoriteChannels(isFavorite: Boolean = true): DataSource.Factory<Int, Channel>

    @Query("UPDATE channel SET isFavorite = :isFavorite WHERE id = :id")
    fun updateChannelFavoriteState(isFavorite: Boolean, id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChannels(articles: List<Channel>)
}