package com.example.newsaggregator.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.newsaggregator.model.data.Article

class ArticleAdapter internal constructor() :
    PagedListAdapter<Article, ArticleViewHolder>(ARTICLE_DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ArticleViewHolder(inflater, parent)
    }

    companion object {

        private val ARTICLE_DIFF_CALLBACK: DiffUtil.ItemCallback<Article> =
            object : DiffUtil.ItemCallback<Article>() {
                override fun areItemsTheSame(
                    oldContribution: Article,
                    newContribution: Article
                ): Boolean {
                    return oldContribution == newContribution
                }

                override fun areContentsTheSame(
                    oldContribution: Article,
                    newContribution: Article
                ): Boolean {
                    return oldContribution.url == newContribution.url && oldContribution.title == newContribution.title
                }
            }
    }
}

/*
class ArticleAdapter(): PagedListAdapter<Article, ArticleAdapter.ArticleViewHolder>(ARTICLE_DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ArticleItemBinding>(
            layoutInflater, viewType, parent, false)
        return ArticleViewHolder(binding)
    }

    class ArticleViewHolder(private val binding: ArticleItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article?) {
            binding.setVariable(BR.item, article)
            binding.executePendingBindings()
        }
    }

    companion object {

        private val ARTICLE_DIFF_CALLBACK: DiffUtil.ItemCallback<Article> =
            object : DiffUtil.ItemCallback<Article>() {
                override fun areItemsTheSame(
                    oldContribution: Article,
                    newContribution: Article
                ): Boolean {
                    return oldContribution == newContribution
                }

                override fun areContentsTheSame(
                    oldContribution: Article,
                    newContribution: Article
                ): Boolean {
                    return oldContribution.url == newContribution.url && oldContribution.title == newContribution.title
                }
            }
    }
}*/
