package com.khaled.jokes.data.implementation

import com.khaled.jokes.data.model.Jokes
import com.khaled.jokes.data.remote.EndPoints
import com.khaled.jokes.domain.repo.IRandomJokes
import javax.inject.Inject

class RandomJokesImpl @Inject constructor(private val endPoints: EndPoints): IRandomJokes {
    override suspend fun getRandomJokes(): Jokes {
        return endPoints.getRandomJokes()
    }
}