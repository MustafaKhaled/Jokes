package com.khaled.jokes.data.local

import android.content.SharedPreferences
import javax.inject.Inject
const val launchCounterKey= "launch_counter"
class SharedPreferenceManager @Inject constructor(private val sharedPreferences: SharedPreferences) {
    fun changeLaunchCounter(count: Int){
        sharedPreferences.edit().putInt(launchCounterKey,count).apply()
    }

    fun getLaunchTimes(): Int{
        return sharedPreferences.getInt(launchCounterKey,1)
    }
}