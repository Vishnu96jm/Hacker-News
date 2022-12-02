package com.zoho.hackernews.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zoho.hackernews.App
import com.zoho.hackernews.R
import com.zoho.hackernews.databinding.ActivityMainBinding
import com.zoho.hackernews.model.News
import com.zoho.hackernews.model.NewsResponse


class MainActivity : AppCompatActivity() {

    private val remoteApi = App.remoteApi
    var newsList = mutableListOf<News>()
    private val adapter = NewsListAdapter(mutableListOf()) { news -> openNews(news) }

    companion object {
        val NEWS_TITLE = "NEWS TITLE"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvItems.adapter = adapter
        remoteApi.fetchNewsList{
            val title = it.title
            val url = it.url
            newsList.add(News(title, url))
            adapter.setNews(newsList)
        }

    }

    private fun openNews(news: News) {
        val title = news.title
      //  val intent = Intent(this@MainActivity, NewsActivity::class.java)
        intent.putExtra(NEWS_TITLE, title)
        startActivity(intent)
      //  startActivity(intentFor<NewsActivity>("title" to news.title))

    }
}