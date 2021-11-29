package com.khaled.jokes.domain.repo

import com.khaled.jokes.data.model.Jokes

interface IRandomJokes {
    suspend fun getRandomJokes(): Jokes?
}
