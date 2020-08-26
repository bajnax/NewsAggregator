package com.example.newsaggregator.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.BR
import com.example.newsaggregator.databinding.ArticleItemBinding
import com.example.newsaggregator.model.data.Article

class ArticleAdapter() : PagedListAdapter<Article, RecyclerView.ViewHolder>(ARTICLE_DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ArticleViewHolder
        getItem(position)?.let {
            viewHolder.bind(it)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ArticleItemBinding.inflate(
            layoutInflater, parent, false
        )
        return ArticleViewHolder(binding)
    }

    class ArticleViewHolder(private val binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

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
}
