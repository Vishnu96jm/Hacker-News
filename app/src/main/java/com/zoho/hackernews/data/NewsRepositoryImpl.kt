package com.zoho.hackernews.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zoho.hackernews.App
import com.zoho.hackernews.data.db.NewsDao
import com.zoho.hackernews.data.model.NewsResponse
import com.zoho.hackernews.db
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class NewsRepositoryImpl : NewsRepository{

    private val newsDao: NewsDao = db.newsDao()
    //private val retrofitClient = RetrofitClient()
    private val apiService = App.apiService
    private val allNews: LiveData<List<NewsResponse>>

    init {
        allNews = newsDao.getAll()
    }
    override fun getSavedNews(): LiveData<List<NewsResponse>> = allNews

    override fun saveNews(news: NewsResponse) {
        thread {
            newsDao.insert(news)
        }
    }

    override fun fetchNews(): LiveData<NewsResponse> {
        val data = MutableLiveData<NewsResponse>()

        apiService.getNewStories().enqueue(object : Callback<List<Int>> {
            override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
                val newsIdList = response.body()!!
                for (newsId in newsIdList){
                    apiService.getNews(newsId).enqueue(object : Callback<NewsResponse> {
                        override fun onResponse(
                            call: Call<NewsResponse>,
                            response: Response<NewsResponse>
                        ) {
                            data.value = response.body()!!
                        }

                        override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                            Log.d(this.javaClass.simpleName, "Failure")
                        }
                    })

                }
            }

            override fun onFailure(call: Call<List<Int>>, t: Throwable) {
                Log.d(this.javaClass.simpleName, "Failure")
            }
        })
        return data

    }

/*
    override fun fetchNews(): LiveData<List<NewsResponse>?> {
        val data = MutableLiveData<List<NewsResponse>>()
        val news = mutableListOf<NewsResponse>()

        apiService.getNewStories().enqueue(object : Callback<List<Int>> {
            override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
                val newsIdList = response.body()!!
                for (newsId in newsIdList){
                    apiService.getNews(newsId).enqueue(object : Callback<NewsResponse> {
                        override fun onResponse(
                            call: Call<NewsResponse>,
                            response: Response<NewsResponse>
                        ) {
                            news.add(response.body()!!)
                            data.value = news
                        }

                        override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                            Log.d(this.javaClass.simpleName, "Failure")
                        }
                    })

                }
            }

            override fun onFailure(call: Call<List<Int>>, t: Throwable) {
                Log.d(this.javaClass.simpleName, "Failure")
            }
        })
        return data

    }
*/

}