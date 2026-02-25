package com.example.yousiccode

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MusicViewModel : ViewModel() {
    var searchResult by mutableStateOf<List<Artist>>(emptyList())
    var isLoading by mutableStateOf(false)


    fun chercherArtiste(nom: String) {
        viewModelScope.launch {
            isLoading = true
            try {
                val response = RetrofitInstance.api.searchArtist(nom)
                searchResult = response.data
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
}