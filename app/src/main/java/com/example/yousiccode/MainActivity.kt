package com.example.yousiccode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBarState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import coil.decode.ImageSource
import com.example.yousiccode.ui.theme.YousicCodeTheme
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {

    private var artiste by mutableStateOf<Artist?>(null)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getArtistById(27)

                artiste = response

            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
        enableEdgeToEdge()
        setContent {
            val search = remember { mutableStateOf("Rechercher ") }
            YousicCodeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column() {
                        Row() {
                            Title(
                            modifier = Modifier.padding(innerPadding)
                            )
                        }
                        Row() {
                            SearchBar(
                                modifier = Modifier.padding(innerPadding),
                                search
                            )
                        }
                        Row() {
                            ArtistCard(artiste)
                        }
                    }
                }
            }
        }
    }
}




@Composable
fun Title( modifier: Modifier = Modifier) {
    Text(
        text = "Yousic",
        modifier = modifier
    )
}

@Composable
fun SearchBar(modifier: Modifier = Modifier, search: MutableState<String>) {
    TextField(search.value, onValueChange = { search.value = it })
    Button( onClick = { /*TODO*/ }) {
        Text(text = "Search")
    }
}


@Composable
fun ArtistCard(artiste : Artist?){
    Column() {
        artiste?.let { currentArtist ->
            Row() {
                Text(text = currentArtist?.name ?: "Chargement")
            }
        } ?: run {
            Text("Chargement en cours...")
        }
        Row() {
            AsyncImage(artiste?.picture, contentDescription = "Artist picture")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    YousicCodeTheme {
        Column {
            Row {
                Title()
            }
            Row {
                SearchBar( search = remember { mutableStateOf("Recher") })
            }
            Row {
                ArtistCard(artiste = Artist(1, "test", "test"))
            }
        }
    }
}