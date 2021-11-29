package com.khaled.jokes.data.implementation

import com.khaled.jokes.data.remote.EndPoints
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class RandomJokesImplTest {
    private val endpoint = mockk<EndPoints>(relaxed = true)

    @Test
    fun verify_getRandomJokes_from_endpoint_called() = runBlockingTest {
        val randomJokesImpl = RandomJokesImpl(endpoint)
        randomJokesImpl.getRandomJokes()
        coVerify { endpoint.getRandomJokes() }
    }
}