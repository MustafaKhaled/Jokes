package com.khaled.jokes.data.remote

import com.khaled.jokes.data.model.Jokes
import retrofit2.http.GET

interface EndPoints {
    @GET("Any?type=twopart&amount=10")
    suspend fun getRandomJokes(): Jokes
}
