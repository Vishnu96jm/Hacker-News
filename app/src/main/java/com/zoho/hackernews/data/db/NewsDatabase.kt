package com.zoho.hackernews.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zoho.hackernews.data.model.NewsResponse

@Database(entities = [NewsResponse::class], version = 1, exportSchema = false)
 abstract class NewsDatabase  : RoomDatabase()  {

    abstract fun newsDao(): NewsDao

    companion object {
        private val lock = Any()
        private const val DB_NAME = "NewsDatabase"
        private var INSTANCE: NewsDatabase? = null

        fun getInstance(application: Application): NewsDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE =
                        Room.databaseBuilder(application, NewsDatabase::class.java, DB_NAME)
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}