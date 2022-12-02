
package com.zoho.hackernews.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zoho.hackernews.databinding.ItemHeadlineBinding
import com.zoho.hackernews.model.News


class NewsListAdapter(private val newsList: MutableList<News>,
                      private var listener: (News) -> Unit):
    RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemHeadlineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindData(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size

    fun setNews(newsList: List<News>) {
        this.newsList.clear()
        this.newsList.addAll(newsList)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(binding: ItemHeadlineBinding) : RecyclerView.ViewHolder(binding.root){
             private val title: TextView = binding.titleText
             private val view = binding.root
             fun bindData(news : News){
                 view.setOnClickListener { listener(news) }
                 title.text = news.title
             }
         }


    }

