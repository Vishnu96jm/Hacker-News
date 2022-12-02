package com.zoho.hackernews.data.model

import com.squareup.moshi.Json


data class News(
    @field:Json(name = "title") var title: String?,
    @field:Json(name = "url") var url: String?,
    @field:Json(name = "type") var type: String?,
    @field:Json(name = "by") var by: String?
    )

