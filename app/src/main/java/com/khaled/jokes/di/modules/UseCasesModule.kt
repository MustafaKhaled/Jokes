package com.khaled.jokes.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaled.jokes.domain.ErrorHandler
import com.khaled.jokes.domain.repo.IAppLaunch
import com.khaled.jokes.domain.repo.IRandomJokes
import com.khaled.jokes.domain.usecases.LaunchTimesUseCase
import com.khaled.jokes.domain.usecases.RandomJokesUseCase
import com.khaled.jokes.presentation.SplashScreenViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {
    @Provides
    fun provideAppLaunchUseCase(iAppLaunch: IAppLaunch, errorHandler: ErrorHandler): LaunchTimesUseCase{
        return LaunchTimesUseCase(iAppLaunch, errorHandler)
    }

    @Provides
    fun provideRandomJokesUseCase(randomJokes: IRandomJokes, errorHandler: ErrorHandler): RandomJokesUseCase{
        return RandomJokesUseCase(randomJokes, errorHandler)
    }
}