package com.example.newsaggregator.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.R
import com.example.newsaggregator.model.data.Article

class ArticleViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.article_item, parent, false
        )
    ) {
    private var tvArticle: TextView? = null


    init {
        tvArticle = itemView.findViewById(R.id.articleTitle)
    }

    fun bind(article: Article?) {
        tvArticle?.text = article?.title
    }
}