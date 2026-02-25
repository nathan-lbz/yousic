package com.example.yousiccode

data class Artist (
    val id: Int,
    val name: String,
    val picture: String
)

data class ListArtists(
    val data: List<Artist>
)


