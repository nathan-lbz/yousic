package com.example.yousiccode

data class Artist (
    val id: Int,
    val name: String,
    val picture: String
)

data class ListArtists(
    val data: List<Artist>
)

data class Song(
    val id: Long,
    val title: String,
    val duration: Int,
    val album: Album
)
data class ListSongs(
    val data: List<Song>
)

data class Album(
    val id: Long,
    val title: String,
    val cover_medium: String
)

data class ListAlbums(
    val data: List<Album>
)



