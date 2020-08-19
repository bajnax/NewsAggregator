package com.example.newsaggregator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.newsaggregator.model.NewsRepository
import com.example.newsaggregator.model.data.Article
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModelModule = module {
    viewModel { MainViewModel(get()) }
}

class MainViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    val allArticles: LiveData<List<Article>> = liveData {
        emit(newsRepository.getAllArticles())
    }

}