package com.khaled.jokes.domain.usecases

import com.khaled.jokes.data.remote.EndPoints
import com.khaled.jokes.domain.ErrorHandler
import com.khaled.jokes.domain.repo.IRandomJokes
import com.khaled.jokes.util.ErrorEntity
import com.khaled.jokes.util.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RandomJokesUseCase @Inject constructor(
    private val randomJokesRepo: IRandomJokes,
    private val errorHandler: ErrorHandler
) {
    fun getRandomJokes() = flow {
        emit(Resource.Loading)
        try {
            val result = randomJokesRepo.getRandomJokes()
            emit(Resource.Success(result))
        } catch (throwable: Throwable) {
            emit(Resource.Failure(errorHandler.getError(throwable)))
        }
    }
}