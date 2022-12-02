package com.zoho.hackernews.data

import androidx.lifecycle.LiveData
import com.zoho.hackernews.data.model.NewsResponse

interface NewsRepository {

    fun getSavedNews(): LiveData<List<NewsResponse>>

    fun saveNews(movie: NewsResponse)

    fun fetchNews(): LiveData<NewsResponse>
   // fun fetchNews(): LiveData<List<NewsResponse>?>

}