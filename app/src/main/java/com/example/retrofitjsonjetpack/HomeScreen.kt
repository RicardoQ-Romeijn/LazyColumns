package com.example.navigationcomposercondatos

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.retrofitjsonjetpack.MovieInfo
import com.example.retrofitjsonjetpack.MovieInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun HomeScreen(navController: NavController) {

    var films by rememberSaveable { mutableStateOf(MovieInfo()) }
    films = Llamada()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(239, 255, 246))
    ) {
        items(films) {
            film ->
            Image(
                painter =  rememberImagePainter(film.movie_banner),
                contentDescription = "Imagen",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(150.dp)
                    .border(3.dp, Color.Black, shape = RoundedCornerShape(50))
                    .clip(shape = RoundedCornerShape(50))
                    .clickable {
                        navController.navigate(
                            Screen.Second.passId_Name(1, film.id)
                        )
                    },
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Composable
fun Llamada(): MovieInfo {
    val contest = LocalContext.current
    var films by rememberSaveable { mutableStateOf(MovieInfo()) }
    val film = MovieInstance.movieInterface.movieInformation()

    film.enqueue(
        object: Callback<MovieInfo> {
            override fun onResponse(
                call: Call<MovieInfo>,
                response: Response<MovieInfo>
            ) {
                val movieInfo: MovieInfo?=response.body()
                if (movieInfo!=null){
                    films = movieInfo
                }
            }
            override fun onFailure(call: Call<MovieInfo>, t: Throwable) {
                Toast.makeText(contest, t.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    )
    return films
}

@Preview(showBackground = true)
@Composable
fun PreviewGreeting() {
    HomeScreen(
        navController = rememberNavController()
    )
}