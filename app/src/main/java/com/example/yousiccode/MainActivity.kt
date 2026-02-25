package com.example.yousiccode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.yousiccode.ui.theme.YousicCodeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    private var artiste by mutableStateOf<Artist?>(null)
    private var listArtistes by mutableStateOf<List<Artist>?>(null)
    val viewModel by viewModels<MusicViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            listArtistes = viewModel.searchResult
            var search = remember {  mutableStateOf("")}
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
                                search,
                                viewModel
                            )
                        }
                        Row() {
                            ArtistsList(listArtistes)
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
        modifier = modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun SearchBar(modifier: Modifier = Modifier, search: MutableState<String>, viewModel: MusicViewModel) {
    TextField(search.value, onValueChange = { search.value = it })
    Button( onClick = { viewModel.chercherArtiste(search.value) }) {
        Text(text = "Search")
    }
}


@Composable
fun ArtistCard(artiste : Artist?){
    Column(Modifier.fillMaxWidth().padding(10.dp)) {
        artiste?.let { currentArtist ->
            Row() {
                Text(text = currentArtist.name,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)

            }
        } ?: run {
            Text("Chargement en cours...", modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)
        }
        Row() {
            AsyncImage(
                artiste?.picture,
                contentDescription = "Artist picture",
                modifier = Modifier.fillMaxWidth()
                    .height(350.dp),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                alignment = Alignment.Center
            )
        }

    }

}

@Composable
fun ArtistsList(artists : List<Artist>?){
    val scrollState = rememberScrollState()
    if (artists != null) {
        LazyColumn() {
            items(artists) { artist ->
                Row() {
                    ArtistCard(artist)
                }
            }
        }
    }else {
        Text("Aucun artiste trouvé")
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
                SearchBar( search = remember { mutableStateOf("Rechercher") }, viewModel = MusicViewModel())
            }
            Row {
                ArtistCard(artiste = Artist(1, "test", "https://api.deezer.com/artist/27/image"))
            }
        }
    }
}