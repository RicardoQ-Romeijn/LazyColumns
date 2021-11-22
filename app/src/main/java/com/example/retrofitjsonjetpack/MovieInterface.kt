package com.example.retrofitjsonjetpack

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val base_api="https://ghibliapi.herokuapp.com/"
interface MovieInterface {
    @GET("films")
    fun movieInformation(): Call<MovieInfo>
}

object MovieInstance {
    val movieInterface:MovieInterface
    init {
        val retrofit=Retrofit.Builder()
            .baseUrl(base_api)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        movieInterface = retrofit.create(MovieInterface::class.java)
    }


}