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
    private var tvArticleTitle: TextView? = null
    private var tvArticleDescription: TextView? = null


    init {
        tvArticleTitle = itemView.findViewById(R.id.articleTitle)
        tvArticleDescription = itemView.findViewById(R.id.articleDescription)
    }

    fun bind(article: Article?) {
        tvArticleTitle?.text = article?.title
        tvArticleDescription?.text = article?.description
    }
}

/*
class ArticleViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(article: Article?) {
        binding.setVariable(BR.item, article)
        binding.executePendingBindings()
    }
}
*/
