package com.example.newsaggregator.view.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.R
import com.example.newsaggregator.model.data.Channel
import com.example.newsaggregator.view.common.OnChannelClickListener

class ChannelViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    val onChannelClickListener: OnChannelClickListener
) :
    RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.channel_item, parent, false
        )
    ) {
    private var tvChannelName: TextView? = null
    private var tvChannelDescription: TextView? = null
    private var favoriteIcon: ImageView? = null
    private var channelContainer: CardView? = null

    init {
        tvChannelName = itemView.findViewById(R.id.channelName)
        tvChannelDescription = itemView.findViewById(R.id.channelDescription)
        favoriteIcon = itemView.findViewById(R.id.favoriteIcon)
        channelContainer = itemView.findViewById(R.id.channelContainer)
    }

    fun bind(channel: Channel?) {
        tvChannelName?.text = channel?.name
        tvChannelDescription?.text = channel?.description
        channel?.let {

            favoriteIcon?.setColorFilter(if (channel.isFavorite) R.color.colorAccent else R.color.colorPrimary)
            favoriteIcon?.setOnClickListener {
                onChannelClickListener.onFavoriteClicked(channel.isFavorite, channel.id)
            }
            channelContainer?.setOnClickListener {
                onChannelClickListener.onItemClicked(channel.id)
            }
        }

    }
}

/*
class ChannelViewHolder(
    private val binding: ViewDataBinding,
    onChannelClickListener: OnChannelClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(channel: Channel?) {
        binding.setVariable(BR.item, channel)
        binding.executePendingBindings()
    }
}*/
