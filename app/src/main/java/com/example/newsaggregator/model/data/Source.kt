package com.example.newsaggregator.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Source(
    @PrimaryKey val roomId: Int,
    val id: String,
    val name: String
)