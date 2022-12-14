package com.zoho.hackernews.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json


@Entity(tableName = "news")
data class NewsResponse (

    @field:Json(name = "by") var by: String? = null,

    @field:Json(name = "descendants") var descendants: Int? = null,

    @PrimaryKey
    @field:Json(name = "id") var id: Int? = null,

    @field:Json(name = "score") var score: Int? = null,

    @field:Json(name = "time") var time: String? = null,

    @field:Json(name = "title") var title: String? = null,

    @field:Json(name = "type") var type: String? = null,

    @field:Json(name = "url") var url: String? = null

)

