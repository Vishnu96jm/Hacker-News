package com.zoho.hackernews.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.zoho.hackernews.data.NewsRepository
import com.zoho.hackernews.data.NewsRepositoryImpl
import com.zoho.hackernews.data.model.NewsResponse

class MainViewModel(private val repository: NewsRepository = NewsRepositoryImpl()) : ViewModel() {

    private val allNews = MediatorLiveData<List<NewsResponse>>()

    init {
        getAllNews()
    }

    fun getSavedNews() = allNews

    private fun getAllNews() {
        allNews.addSource(repository.getSavedNews()) { newsResponse ->
            allNews.postValue(newsResponse)
        }
    }
}
