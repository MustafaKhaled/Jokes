package com.khaled.jokes.di.modules

import com.khaled.jokes.domain.ErrorHandler
import com.khaled.jokes.domain.repo.IAppLaunch
import com.khaled.jokes.domain.repo.IRandomJokes
import com.khaled.jokes.domain.usecases.LaunchTimesUseCase
import com.khaled.jokes.domain.usecases.RandomJokesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {
    @Provides
    fun provideAppLaunchUseCase(iAppLaunch: IAppLaunch, errorHandler: ErrorHandler): LaunchTimesUseCase {
        return LaunchTimesUseCase(iAppLaunch, errorHandler)
    }

    @Provides
    fun provideRandomJokesUseCase(randomJokes: IRandomJokes, errorHandler: ErrorHandler): RandomJokesUseCase {
        return RandomJokesUseCase(randomJokes, errorHandler)
    }
}
