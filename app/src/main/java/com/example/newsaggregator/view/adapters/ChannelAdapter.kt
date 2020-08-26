package com.example.newsaggregator.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.BR
import com.example.newsaggregator.R
import com.example.newsaggregator.databinding.ChannelItemBinding
import com.example.newsaggregator.model.data.Channel
import com.example.newsaggregator.view.common.OnChannelClickListener

class ChannelAdapter internal constructor(private val onChannelClickListener: OnChannelClickListener) :
    PagedListAdapter<Channel, RecyclerView.ViewHolder>(CHANNEL_DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ChannelViewHolder
        getItem(position)?.let {
            viewHolder.bind(it)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChannelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ChannelItemBinding.inflate(
            layoutInflater, parent, false
        )
        return ChannelViewHolder(binding, onChannelClickListener)
    }

    class ChannelViewHolder(
        private val binding: ChannelItemBinding,
        private val onChannelClickListener: OnChannelClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(channel: Channel?) {
            binding.setVariable(BR.item, channel)
            binding.executePendingBindings()
            channel?.let {

                binding.favoriteIcon.setColorFilter(if (channel.isFavorite) R.color.colorAccent else R.color.colorPrimary)
                binding.favoriteIcon.setOnClickListener {
                    onChannelClickListener.onFavoriteClicked(channel.isFavorite, channel.id)
                }
                binding.channelContainer.setOnClickListener {
                    onChannelClickListener.onItemClicked(channel.id)
                }
            }
        }
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
