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

    @Query("SELECT * FROM channel WHERE favoriteState == :isFavoriteChannel")
    fun getPagedFavoriteChannels(isFavoriteChannel: Int): DataSource.Factory<Int, Channel>

    @Query("UPDATE channel SET favoriteState = :isFavoriteChannel WHERE id == :id")
    suspend fun updateChannelFavoriteState(isFavoriteChannel: Int, id: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChannels(articles: List<Channel>)
}