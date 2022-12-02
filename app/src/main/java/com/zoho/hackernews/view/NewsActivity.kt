package com.zoho.hackernews.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.zoho.hackernews.databinding.ActivityNewsBinding
import com.zoho.hackernews.view.MainActivity.Companion.NEWS_BY
import com.zoho.hackernews.view.MainActivity.Companion.NEWS_TITLE
import com.zoho.hackernews.view.MainActivity.Companion.NEWS_TYPE
import com.zoho.hackernews.view.MainActivity.Companion.NEWS_URL

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(NEWS_TITLE)
        val url = intent.getStringExtra(NEWS_URL)
        val by = intent.getStringExtra(NEWS_BY)
        val type = intent.getStringExtra(NEWS_TYPE)

        binding.titleText.text = title
        binding.byValue.text = by
        binding.typeValue.text = type

        val webView : WebView = binding.webView
        webView.webViewClient = WebViewClient()
        url?.let { webView.loadUrl(it) }
    }
}