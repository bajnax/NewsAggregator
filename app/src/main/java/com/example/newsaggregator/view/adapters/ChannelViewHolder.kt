package com.example.newsaggregator.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.R
import com.example.newsaggregator.model.data.Channel

class ChannelViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.channel_item, parent, false
        )
    ) {
    private var tvChannelName: TextView? = null


    init {
        tvChannelName = itemView.findViewById(R.id.channelName)
    }

    fun bind(channel: Channel?) {
        tvChannelName?.text = channel?.name
    }
}