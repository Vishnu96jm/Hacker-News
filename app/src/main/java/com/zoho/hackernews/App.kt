package com.zoho.hackernews

import android.app.Application
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.zoho.hackernews.data.db.NewsDatabase
import com.zoho.hackernews.data.networking.buildApiService

lateinit var db: NewsDatabase

class App : Application() {

    companion object {
        private lateinit var instance: App
        const val BASE_URL = "https://hacker-news.firebaseio.com/"

        //To expose the RemoteApi to the rest of the app
        val apiService by lazy { buildApiService() }

    }

    override fun onCreate() {
        super.onCreate()
        db = NewsDatabase.getInstance(this)
        instance = this
    }

}

inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_INDEFINITE, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
}

fun Snackbar.action(action: String, listener: (View) -> Unit) {
    setAction(action, listener)
}