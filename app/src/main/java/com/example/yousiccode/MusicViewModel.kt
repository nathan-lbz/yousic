package com.example.yousiccode

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MusicViewModel : ViewModel() {
    var searchArtist by mutableStateOf<List<Artist>?>(null)
    var searchSong by mutableStateOf<List<Song>?>(null)
    var searchAlbum by mutableStateOf<List<Album>?>(null)

    var isLoading by mutableStateOf(false)


    fun chercherArtiste(nom: String) {
        viewModelScope.launch {
            isLoading = true
            try {
                val artist = RetrofitInstance.apiDeezer.searchArtist(nom)
                searchArtist = artist.data
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

    fun chercherSon(nom: String) {
        viewModelScope.launch {
            isLoading = true
            try {
                val song = RetrofitInstance.apiDeezer.searchSong(nom)
                searchSong = song.data
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

    fun chercherAlbum(nom: String) {
        viewModelScope.launch {
            isLoading = true
            try {
                val song = RetrofitInstance.apiDeezer.searchAlbum(nom)
                searchAlbum = song.data
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
}