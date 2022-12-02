package com.zoho.hackernews.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zoho.hackernews.App
import com.zoho.hackernews.databinding.ActivityMainBinding
import com.zoho.hackernews.model.News


class MainActivity : AppCompatActivity() {

    private val remoteApi = App.remoteApi
    var newsList = mutableListOf<News>()
    private val adapter = NewsListAdapter(mutableListOf()) { news -> openNews(news) }

    companion object {
        const val NEWS_TITLE = "NEWS TITLE"
        const val NEWS_TYPE = "NEWS TYPE"
        const val NEWS_URL = "URL"
        const val NEWS_BY = "BY"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvItems.adapter = adapter
        remoteApi.fetchNewsList{
            val title = it.title
            val type = it.type
            val by = it.by
            val url = it.url
            newsList.add(News(title, url, type, by))
            adapter.setNews(newsList)
        }

    }

    private fun openNews(news: News) {
        val title = news.title
        val type = news.type
        val url = news.url
        val by = news.by

        val intent = Intent(this@MainActivity, NewsActivity::class.java)
        intent.putExtra(NEWS_TITLE, title)
        intent.putExtra(NEWS_TYPE, type)
        intent.putExtra(NEWS_URL, url)
        intent.putExtra(NEWS_BY, by)
        startActivity(intent)
      //  startActivity(intentFor<NewsActivity>("title" to news.title))
    }
}