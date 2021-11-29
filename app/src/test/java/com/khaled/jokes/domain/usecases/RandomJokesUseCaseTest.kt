package com.khaled.jokes.domain.usecases

import com.khaled.jokes.data.model.Jokes
import com.khaled.jokes.domain.ErrorHandler
import com.khaled.jokes.domain.repo.IRandomJokes
import com.khaled.jokes.util.Resource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class RandomJokesUseCaseTest {
    @Test
    fun verify_getRandomJokes_shouldReturnData() = runBlockingTest {
        val randomJokes = mockk<IRandomJokes>(relaxed = true)
        val jokes = mockk<Jokes>()
        val errorHandler = mockk<ErrorHandler>(relaxed = true)
        val list = ArrayList<Resource<Jokes>>()
        val useCase = RandomJokesUseCase(randomJokes, errorHandler)
        coEvery { randomJokes.getRandomJokes() } returns jokes
        useCase.getRandomJokes().collect {
            list.add(it as Resource<Jokes>)
        }
        assertEquals(list, listOf(Resource.Loading, Resource.Success(jokes)))
    }

    @Test
    fun verify_getRandomJokes_shouldReturnFailure() = runBlockingTest {
        val randomJokes = mockk<IRandomJokes>(relaxed = true)
        val errorHandler = mockk<ErrorHandler>(relaxed = true)
        val exception = mockk<Exception>(relaxed = true)
        val list = ArrayList<Resource<Jokes>>()
        val useCase = RandomJokesUseCase(randomJokes, errorHandler)
        coEvery { randomJokes.getRandomJokes() } throws exception
        useCase.getRandomJokes().collect {
            list.add(it as Resource<Jokes>)
        }
        assertEquals(list, listOf(Resource.Loading, Resource.Failure(errorHandler.getError(exception))))
    }
}
