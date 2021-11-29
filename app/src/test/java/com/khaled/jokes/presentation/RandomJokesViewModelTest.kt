package com.khaled.jokes.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.khaled.jokes.data.model.Jokes
import com.khaled.jokes.domain.ErrorHandler
import com.khaled.jokes.domain.usecases.RandomJokesUseCase
import com.khaled.jokes.util.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class RandomJokesViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun verify_getRandomJokes_shouldReturnData() = runBlockingTest{
        val useCase = mockk<RandomJokesUseCase>(relaxed = true)
        val jokes = mockk<Jokes>(relaxed = true)
        val expected = Resource.Success(jokes)
        val viewModel = RandomJokesViewModel(useCase)
        viewModel.randomJokesLiveData.observeForever{}
        coEvery { useCase.getRandomJokes() } returns flowOf(Resource.Success(jokes))
        viewModel.getRandomJokes()
        assertEquals(expected, viewModel.randomJokesLiveData.value)
    }

    @Test
    fun verify_getRandomJokes_shouldReturnError() = runBlockingTest{
        val useCase = mockk<RandomJokesUseCase>(relaxed = true)
        val errorHandler = mockk<ErrorHandler>(relaxed = true)
        val exception =  mockk<Exception>(relaxed = true)
        val expected = Resource.Failure(errorHandler.getError(exception))
        val viewModel = RandomJokesViewModel(useCase)
        viewModel.randomJokesLiveData.observeForever{}
        coEvery { useCase.getRandomJokes() } returns flowOf(Resource.Failure(errorHandler.getError(exception)))
        viewModel.getRandomJokes()
        assertEquals(expected, viewModel.randomJokesLiveData.value)
    }

    @Test
    fun verify_getRandomJokes_shouldLoading() = runBlockingTest{
        val useCase = mockk<RandomJokesUseCase>(relaxed = true)
        val exception = Resource.Loading
        val viewModel = RandomJokesViewModel(useCase)
        viewModel.randomJokesLiveData.observeForever {  }
        coEvery { useCase.getRandomJokes() } returns  flowOf(exception)
        viewModel.getRandomJokes()
        assertEquals(
            exception,
            viewModel.randomJokesLiveData.value
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}