package com.zoho.hackernews.data

import androidx.lifecycle.LiveData
import com.zoho.hackernews.data.model.News
import com.zoho.hackernews.data.model.NewsResponse

interface NewsRepository {

    fun getSavedNews(): LiveData<List<NewsResponse>>

    fun saveNews(movie: News)

    fun fetchNewsList(query: String): LiveData<List<NewsResponse>?>

}