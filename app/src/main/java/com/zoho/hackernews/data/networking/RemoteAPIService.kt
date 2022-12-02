package com.zoho.hackernews.data.networking

import com.zoho.hackernews.data.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteAPIService {

    @GET("v0/newstories.json?print=pretty")
    fun getNewStories(): Call<List<Int>>

    @GET("v0/item/{newsId}.json?print=pretty")
    fun getNews(@Path("newsId") id: Int): Call<NewsResponse>
}