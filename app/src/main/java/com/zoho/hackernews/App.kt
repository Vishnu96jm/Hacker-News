package com.zoho.hackernews

import android.app.Application
import com.zoho.hackernews.data.db.NewsDatabase
import com.zoho.hackernews.data.networking.RemoteApi
import com.zoho.hackernews.data.networking.buildApiService

lateinit var db: NewsDatabase

class App : Application() {

    companion object {
        private lateinit var instance: App
        const val BASE_URL = "https://hacker-news.firebaseio.com/"

        //To expose the RemoteApi to the rest of the app
        val apiService by lazy { buildApiService() }

        val remoteApi by lazy { RemoteApi(apiService) }
    }

    override fun onCreate() {
        super.onCreate()
        db = NewsDatabase.getInstance(this)
        instance = this
    }
}