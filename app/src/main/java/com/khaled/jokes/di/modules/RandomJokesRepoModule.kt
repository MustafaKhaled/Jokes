package com.khaled.jokes.di.modules

import com.khaled.jokes.data.implementation.RandomJokesImpl
import com.khaled.jokes.domain.repo.IRandomJokes
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RandomJokesRepoModule {
    @Binds
    fun bindRandomJokesRepoModule(randomJokesImpl: RandomJokesImpl): IRandomJokes
}