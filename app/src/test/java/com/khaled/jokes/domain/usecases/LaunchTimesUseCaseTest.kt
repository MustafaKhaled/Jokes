package com.khaled.jokes.domain.usecases

import com.khaled.jokes.domain.ErrorHandler
import com.khaled.jokes.domain.repo.IAppLaunch
import com.khaled.jokes.util.Resource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class LaunchTimesUseCaseTest {
    @Test
    fun verify_getLaunchTimes_shouldReturnData() = runBlockingTest{
        val appLaunch = mockk<IAppLaunch>(relaxed = true)
        val errorHandler = mockk<ErrorHandler>(relaxed = true)
        val list = ArrayList<Resource<Int>>()

        val useCase = LaunchTimesUseCase(appLaunch, errorHandler)
        coEvery { appLaunch.getLaunchTimes() } returns 2
        useCase.getLaunchTimes().collect {
            list.add(it)
        }
        assertEquals(list, listOf(Resource.Loading, Resource.Success(2)))
    }

    @Test
    fun verify_getLaunchTimes_shouldReturnFailure() = runBlockingTest{
        val appLaunch = mockk<IAppLaunch>(relaxed = true)
        val errorHandler = mockk<ErrorHandler>(relaxed = true)
        val list = ArrayList<Resource<Int>>()
        val exception = mockk<Exception>(relaxed = true)
        val useCase = LaunchTimesUseCase(appLaunch, errorHandler)
        coEvery { appLaunch.getLaunchTimes() } throws exception
        useCase.getLaunchTimes().collect {
            list.add(it)
        }
        assertEquals(list, listOf(Resource.Loading, Resource.Failure(errorHandler.getError(exception))))
    }
}