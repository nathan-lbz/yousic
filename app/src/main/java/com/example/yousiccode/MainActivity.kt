package com.example.yousiccode

import android.os.Bundle
import android.provider.MediaStore
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
    private var listArtistes by mutableStateOf<List<Artist>?>(null)
    private var listSon by mutableStateOf<List<Song>?>(null)
    private var listAlbum by mutableStateOf<List<Album>?>(null)
    val viewModel by viewModels<MusicViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            listSon = viewModel.searchSong
            listArtistes = viewModel.searchArtist
            listAlbum = viewModel.searchAlbum

            YousicCodeTheme {
                var searchType = remember {  mutableStateOf("Artiste")}
                var search = remember {  mutableStateOf("")}
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column() {
                        Row() {
                            Title(
                            modifier = Modifier.padding(innerPadding)
                            )
                        }

                        Row() {
                            SearchBar(
                                search,
                                viewModel,
                            )
                        }


                        SearchType(searchType)
                        if (searchType.value == "Artiste") {
                            Row() {
                                ListArtists(listArtistes )
                            }
                        } else if (searchType.value == "Chanson") {
                            Row() {
                                ListSongs(listSon)
                            }
                        }else if (searchType.value == "Album") {
                            Row() {
                                ListAlbums(listAlbum)
                            }

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
fun SearchBar(search: MutableState<String>, viewModel: MusicViewModel) {
    TextField(search.value, onValueChange = { search.value = it })
    val chercherTout = {
        viewModel.chercherArtiste(search.value)
        viewModel.chercherSon(search.value)
        viewModel.chercherAlbum(search.value)
    }
    Button( onClick =  chercherTout) {
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
                    textAlign = TextAlign.Center
                )

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
fun SongCard(song : Song?){
    Column(Modifier.fillMaxWidth().padding(10.dp)) {
        song?.let { currentSong ->
            Row() {
                Text(text = currentSong.title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)

            }
            Row() {
                AsyncImage(
                    currentSong.album.cover_medium,
                    contentDescription = "Artist picture",
                    modifier = Modifier.fillMaxWidth()
                        .height(350.dp),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                    alignment = Alignment.Center
                )
            }
        } ?: run {
            Text("Chargement en cours...", modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)
        }


    }
}

@Composable
fun AlbumCard(album : Album?){
    Column(Modifier.fillMaxWidth().padding(10.dp)) {
        album?.let { currentAlbum ->
            Row() {
                Text(text = currentAlbum.title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)

            }
            Row() {
                AsyncImage(
                    currentAlbum.cover_medium,
                    contentDescription = "Album picture",
                    modifier = Modifier.fillMaxWidth()
                        .height(350.dp),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                    alignment = Alignment.Center
                )
            }
        } ?: run {
            Text("Chargement en cours...", modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)
        }


    }
}

@Composable
fun ListArtists(artists : List<Artist>?) {
    if (artists == null)return
    if (!artists.isEmpty()) {
        CardsList(artists as List<Object>, composable = { ArtistCard( it as Artist) })
    } else {
        Text("Aucun artiste trouvé")
    }
}
@Composable
fun ListSongs(songs : List<Song>?){
    if (songs == null) return
    if (!songs.isEmpty()) {
        CardsList(songs as List<Object>, composable = { SongCard( it as Song) })
    } else {
        Text("Aucune musique trouvé")
    }
}

@Composable
fun ListAlbums(albums : List<Album>?){
    if (albums == null) return
    if (!albums.isEmpty()) {
        CardsList(albums as List<Object>, composable = { AlbumCard( it as Album) })
    } else {
        Text("Aucun album trouvé")
    }
}

@Composable
fun CardsList(list: List<Object>, composable: @Composable (Object) -> Unit){
    LazyColumn() {
        items(list){
            Row() {
                composable(it)
            }
        }
    }
}

@Composable
fun SearchType(searchT: MutableState<String> = remember {  mutableStateOf("Chanson")}){
    val typeList = listOf("Artiste", "Chanson", "Album")
    Row() {
        for (type in typeList) {
            if (searchT.value != type) {
                Button(onClick = { searchT.value = type }) {
                    Text(type)
                }
            }else {
                Button(onClick = { searchT.value = type },  enabled = false) {
                    Text(type)
                }

            }
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
                SearchBar(
                    search = remember { mutableStateOf("Rechercher") },
                    viewModel = MusicViewModel()
                )
            }
            SearchType()
            Row {
                ArtistCard(artiste = Artist(1, "test", "https://api.deezer.com/artist/27/image"))
            }
        }
    }
}

