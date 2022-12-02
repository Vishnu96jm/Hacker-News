package com.zoho.hackernews.data.networking

import com.zoho.hackernews.App
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


fun buildClient(): OkHttpClient =
    OkHttpClient.Builder()
        .build()


fun buildRetrofit(): Retrofit {
    return Retrofit.Builder()
        .client(buildClient())
        .baseUrl(App.BASE_URL)
        /*This will add Moshi converter to Retrofit, which will automatically parse the JSON
        and give the object of the type needed.*/
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()
}

fun buildApiService(): RemoteAPIService =
    buildRetrofit().create(RemoteAPIService::class.java)