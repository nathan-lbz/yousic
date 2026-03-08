package com.example.yousiccode

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyServerApi {
    @POST("share")
    suspend fun partagerArtiste(@Body post: Post): Response<Unit>

    @GET("posts")
    suspend fun getMurDePartages(): List<Post>

}