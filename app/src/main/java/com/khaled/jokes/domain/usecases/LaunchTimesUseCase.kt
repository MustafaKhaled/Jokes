package com.khaled.jokes.domain.usecases

import com.khaled.jokes.domain.ErrorHandler
import com.khaled.jokes.domain.repo.IAppLaunch
import com.khaled.jokes.util.Resource
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class LaunchTimesUseCase @Inject constructor(
    private val iAppLaunch: IAppLaunch,
    private val errorHandler: ErrorHandler
) {
    fun getLaunchTimes() = flow {
        emit(Resource.Loading)
        try {
            val launchTimes = iAppLaunch.getLaunchTimes()
            updateLaunchTimes(launchTimes)
            emit(Resource.Success(launchTimes))
        } catch (ex: Throwable) {
            emit(Resource.Failure(errorHandler.getError(ex)))
        }
    }

    private fun updateLaunchTimes(count: Int) {
        iAppLaunch.updateLaunchTimes(count + 1)
    }
}