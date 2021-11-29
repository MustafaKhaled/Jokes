package com.khaled.jokes.di.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val preferenceName = "app_pref"
@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesModule {
    @Singleton
    @Provides
    fun providesSharedPreferenceInstance(@ApplicationContext context: Context): SharedPreferences{
        return context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
    }
}