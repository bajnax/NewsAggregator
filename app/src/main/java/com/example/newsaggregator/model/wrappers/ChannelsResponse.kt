package com.example.newsaggregator.model.wrappers

import com.example.newsaggregator.model.data.Channel
import com.google.gson.annotations.SerializedName

data class ChannelsResponse(
    val status: String,
    @SerializedName("sources")
    val channels: List<Channel>,
    val message: String?
)