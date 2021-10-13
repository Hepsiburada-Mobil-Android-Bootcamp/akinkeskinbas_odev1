package com.akin.figmatask.api


import com.akin.figmatask.data.Data
import com.akin.figmatask.data.Imdb
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleApi {

    @GET("Trailer/k_w4cpx73g/{movie_id}")

     fun getPost(@Path("movie_id") movieId:String): Call<Imdb>
}