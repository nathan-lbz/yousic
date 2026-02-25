package com.example.yousiccode

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeezerApiService {
    @GET("artist/{id}")
    suspend fun getArtistById(@Path("id") id: Int): Artist

    @GET("search/artist")
    suspend fun searchArtist(
        @Query("q") query: String
    ): ListArtists
}


