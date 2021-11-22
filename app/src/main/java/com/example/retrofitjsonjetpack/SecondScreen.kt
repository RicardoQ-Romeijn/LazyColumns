package com.example.navigationcomposercondatos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.retrofitjsonjetpack.Films
import com.example.retrofitjsonjetpack.MovieInfo

@Composable
fun SecondScreen(id: Int, filmID:String) {

    var films by rememberSaveable { mutableStateOf(MovieInfo()) }
    films = Llamada()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(239, 255, 246))
    ) {
        var selectedMovie = getFilm(films, filmID)

        if (selectedMovie != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                showImage(url = selectedMovie.image)

                Column(
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    showTitle(texto = "Titulo:")
                    showText(texto = selectedMovie.title)
                    showTitle(texto = "Titulo Original Romanizado:")
                    showText(texto = selectedMovie.original_title_romanised)
                    showTitle(texto = "Titulo Original:")
                    showText(texto = selectedMovie.original_title)
                    showTitle(texto = "Productor:")
                    showText(texto = selectedMovie.producer)
                    showTitle(texto = "Director:")
                    Text(
                        text = selectedMovie.director,
                        fontSize = 15.sp
                    )
                }

            }

            Text(
                text = selectedMovie.description,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 5.dp, start = 10.dp, end = 10.dp)
            )

        }
    }

}

fun getFilm(
    films: ArrayList<Films>,
    filmID: String
): Films? {
    for (film in films) {
        if (film.id.equals(filmID)) {
            return film
        }
    }
    return null
}

@Composable
fun showImage(url: String) {
    Image(
        painter =  rememberImagePainter(url),
        contentDescription = "Imagen",
        modifier = Modifier
            .height(300.dp)
            .width(200.dp)
            .padding(5.dp)
            .border(3.dp, Color.Black, shape = RoundedCornerShape(5))
            .clip(shape = RoundedCornerShape(5)),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun showText(texto: String) {
    Text(
        text = texto,
        fontSize = 16.sp,
        modifier = Modifier.padding(bottom = 10.dp)
    )
}

@Composable
fun showTitle(texto: String) {
    Text(
        text = texto,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 5.dp)
    )
}