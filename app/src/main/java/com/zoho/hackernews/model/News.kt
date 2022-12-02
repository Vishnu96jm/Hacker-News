package com.zoho.hackernews.model

import com.squareup.moshi.Json


data class News(
    @field:Json(name = "title") var title: String?,
    @field:Json(name = "url") var url: String?
    )
