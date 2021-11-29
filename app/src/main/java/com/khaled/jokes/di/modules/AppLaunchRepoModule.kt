package com.khaled.jokes.di.modules

import com.khaled.jokes.data.implementation.AppLaunchTimesImpl
import com.khaled.jokes.domain.repo.IAppLaunch
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface AppLaunchRepoModule {
    @Binds
    fun bindAppLaunchRepoModule(appLaunchTimesImpl: AppLaunchTimesImpl): IAppLaunch
}
