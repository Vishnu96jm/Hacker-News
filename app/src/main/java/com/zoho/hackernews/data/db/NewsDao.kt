package com.zoho.hackernews.data.db

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zoho.hackernews.data.model.News
import com.zoho.hackernews.data.model.NewsResponse

interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: News)

    @Query("select * from news")
    fun getAll(): LiveData<List<NewsResponse>>
}