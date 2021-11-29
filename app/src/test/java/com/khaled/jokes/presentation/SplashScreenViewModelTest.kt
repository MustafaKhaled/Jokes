package com.khaled.jokes.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.khaled.jokes.domain.ErrorHandler
import com.khaled.jokes.domain.usecases.LaunchTimesUseCase
import com.khaled.jokes.util.Resource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class SplashScreenViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun verify_getLaunchTimes_shouldReturnData() = runBlockingTest {
        val useCase = mockk<LaunchTimesUseCase>(relaxed = true)
        val exceptedCounter = 3
        mockk<Resource.Loading>()
        val viewModel = SplashScreenViewModel(useCase)
        viewModel.launchTimeLiveData.observeForever { }
        coEvery { useCase.getLaunchTimes() } returns flowOf(Resource.Success(exceptedCounter))
        viewModel.getAppLaunchTimes()
        assertEquals(
            Resource.Success(exceptedCounter),
            viewModel.launchTimeLiveData.value
        )
    }

    @Test
    fun verify_getLaunchTimes_shouldThrowException() = runBlockingTest {
        val useCase = mockk<LaunchTimesUseCase>(relaxed = true)
        val exception = mockk<Exception>()
        val errorHandler = mockk<ErrorHandler>(relaxed = true)
        mockk<Resource.Loading>()
        val viewModel = SplashScreenViewModel(useCase)
        viewModel.launchTimeLiveData.observeForever { }
        coEvery { useCase.getLaunchTimes() } returns flowOf(Resource.Failure(errorHandler.getError(exception)))
        viewModel.getAppLaunchTimes()
        assertEquals(
            Resource.Failure(errorHandler.getError(exception)),
            viewModel.launchTimeLiveData.value
        )
    }

    @Test
    fun verify_getLaunchTimes_shouldLoading() = runBlockingTest {
        val useCase = mockk<LaunchTimesUseCase>(relaxed = true)
        val exception = Resource.Loading
        mockk<Resource.Loading>()
        val viewModel = SplashScreenViewModel(useCase)
        viewModel.launchTimeLiveData.observeForever { }
        coEvery { useCase.getLaunchTimes() } returns flowOf(exception)
        viewModel.getAppLaunchTimes()
        assertEquals(
            exception,
            viewModel.launchTimeLiveData.value
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}
