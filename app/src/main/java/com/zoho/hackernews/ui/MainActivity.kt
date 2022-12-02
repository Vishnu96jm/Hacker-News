package com.zoho.hackernews.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.zoho.hackernews.App
import com.zoho.hackernews.databinding.ActivityMainBinding
import com.zoho.hackernews.viewmodel.MainViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.zoho.hackernews.R
import com.zoho.hackernews.action
import com.zoho.hackernews.data.model.NewsResponse
import com.zoho.hackernews.snack


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter = NewsListAdapter(mutableListOf()) { news -> openNews(news) }

    companion object {
        const val NEWS_TITLE = "NEWS TITLE"
        const val NEWS_TYPE = "NEWS TYPE"
        const val NEWS_URL = "URL"
        const val NEWS_BY = "BY"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvItems.adapter = adapter

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        fetchNews()
        viewModel.getSavedNews().observe(this, Observer { newsList ->
            hideLoading()
            newsList?.let {
                adapter.setNews(newsList)
            }
        })

    }

    private fun openNews(news: NewsResponse) {
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
    }

    private fun showLoading() {
        binding.rvItems.adapter = adapter

        binding.progressBar.visibility = View.VISIBLE
        binding.rvItems.isEnabled = false
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
        binding.rvItems.isEnabled = true
    }

    private fun showMessage() {
        binding.mainLayout.snack(getString(R.string.network_error), Snackbar.LENGTH_INDEFINITE) {
            action(getString(R.string.ok)) {
                fetchNews()
            }
        }
    }

    private fun fetchNews() {
        showLoading()
        viewModel.fetchNews().observe(this, Observer { news ->
            hideLoading()
            if (news == null) {
                  showMessage()
            } else {
                viewModel.saveMovie(news)
            }
        })
    }
}