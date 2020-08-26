package com.example.newsaggregator.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.newsaggregator.R
import com.example.newsaggregator.app.Constants
import com.example.newsaggregator.databinding.FragmentSearchArticlesBinding
import com.example.newsaggregator.view.adapters.ArticleAdapter
import com.example.newsaggregator.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchArticlesFragment : BaseFragment<FragmentSearchArticlesBinding>() {

    override val layoutId = R.layout.fragment_search_articles

    val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.setIndex(arguments?.getInt(Constants.ARG_SECTION_NUMBER) ?: 1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArticleAdapter()

        binding.articlesRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.articlesRecyclerView.adapter = adapter

        mainViewModel.fetchArticles()
        mainViewModel.pagedArticles.observe(viewLifecycleOwner, Observer { pagedArticles ->
            adapter.submitList(pagedArticles)
        })
    }

    companion object {

        @JvmStatic
        fun newInstance(sectionNumber: Int): SearchArticlesFragment {
            return SearchArticlesFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}
