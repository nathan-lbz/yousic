package com.example.yousiccode

import retrofit2.http.GET
import retrofit2.http.Path

interface DeezerApiService {
    @GET("artist/{id}")
    suspend fun getArtistById(@Path("id") id: Int): Artist
}


