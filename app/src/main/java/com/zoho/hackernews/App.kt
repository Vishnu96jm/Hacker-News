package com.zoho.hackernews

import android.app.Application
import com.zoho.hackernews.networking.RemoteApi
import com.zoho.hackernews.networking.buildApiService

class App : Application() {

    companion object {
        private lateinit var instance: App

        //To expose the RemoteApi to the rest of the app
        private val apiService by lazy { buildApiService() }

        //you have to change all the uses of the RemoteApi to use this one from the App.kt file
        val remoteApi by lazy { RemoteApi(apiService) }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}