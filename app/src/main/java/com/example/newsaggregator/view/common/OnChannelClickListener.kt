package com.example.newsaggregator.view.common

interface OnChannelClickListener {
    fun onItemClicked(id: String)
    fun onFavoriteClicked(selectedState: Boolean, id: String)
}