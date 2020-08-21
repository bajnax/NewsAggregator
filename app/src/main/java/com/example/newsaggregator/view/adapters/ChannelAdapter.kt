package com.example.newsaggregator.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.newsaggregator.model.data.Channel

class ChannelAdapter internal constructor() :
    PagedListAdapter<Channel, ChannelViewHolder>(CHANNEL_DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChannelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ChannelViewHolder(inflater, parent)
    }

    companion object {

        private val CHANNEL_DIFF_CALLBACK: DiffUtil.ItemCallback<Channel> =
            object : DiffUtil.ItemCallback<Channel>() {
                override fun areItemsTheSame(
                    oldContribution: Channel,
                    newContribution: Channel
                ): Boolean {
                    return oldContribution == newContribution
                }

                override fun areContentsTheSame(
                    oldContribution: Channel,
                    newContribution: Channel
                ): Boolean {
                    return oldContribution.url == newContribution.url &&
                            oldContribution.id == newContribution.id &&
                            oldContribution.isFavorite == newContribution.isFavorite
                }
            }
    }
}