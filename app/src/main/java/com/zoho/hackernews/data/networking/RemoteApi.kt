package com.zoho.hackernews.data.networking

import android.util.Log
import com.zoho.hackernews.data.model.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val BASE_URL = "https://hacker-news.firebaseio.com/"
class RemoteApi(private val apiService: RemoteAPIService)  {

    fun fetchNewsList(onComplete : (NewsResponse) -> Unit){
      //  val news = mutableListOf<NewsResponse>()
        apiService.getNewStories().enqueue(object : Callback<List<Int>>{
            override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
                val newsIdList = response.body()!!
                for (newsId in newsIdList){
                    apiService.getNews(newsId).enqueue(object : Callback<NewsResponse>{
                        override fun onResponse(
                            call: Call<NewsResponse>,
                            response: Response<NewsResponse>
                        ) {
                          //  news.add(response.body()!!)
                            onComplete.invoke(response.body()!!)
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
    }

}