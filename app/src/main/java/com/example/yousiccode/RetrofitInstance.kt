package com.example.yousiccode

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL_DEEZER: String = "https://api.deezer.com/"

    val apiDeezer: DeezerApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_DEEZER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeezerApiService::class.java)
    }


    private const val BASE_URL_SERVER = "http://10.0.2.2:8080/"

    val apiServer: MyServerApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_SERVER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyServerApi::class.java)
    }
}