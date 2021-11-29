package com.khaled.jokes.di.modules

import com.khaled.jokes.data.ErrorHandlerImpl
import com.khaled.jokes.domain.ErrorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ErrorHandlerModule {
    @Binds
    fun bindErrorHandlerModule(errorHandler: ErrorHandlerImpl): ErrorHandler
}
