package com.example.newsaggregator.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsaggregator.R
import com.example.newsaggregator.databinding.ActivityMainBinding
import com.example.newsaggregator.view.adapters.ArticleAdapter
import com.example.newsaggregator.view.adapters.ChannelAdapter
import com.example.newsaggregator.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        /*val adapter = ArticleAdapter()

        articlesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.articlesRecyclerView.adapter = adapter

        mainViewModel.fetchArticles()
        mainViewModel.pagedArticles.observe(this, Observer { pagedArticles ->
            adapter.submitList(pagedArticles)
        })*/

        val adapter = ChannelAdapter()

        articlesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.articlesRecyclerView.adapter = adapter

        mainViewModel.fetchChannels()
        mainViewModel.pagedChannels.observe(this, Observer { pagedChannels ->
            adapter.submitList(pagedChannels)
        })


    }
}
