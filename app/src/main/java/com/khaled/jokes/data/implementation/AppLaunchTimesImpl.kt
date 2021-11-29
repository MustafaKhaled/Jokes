package com.khaled.jokes.data.implementation

import com.khaled.jokes.data.local.SharedPreferenceManager
import com.khaled.jokes.domain.repo.IAppLaunch
import javax.inject.Inject

class AppLaunchTimesImpl @Inject constructor(private val sharedPreferenceManager: SharedPreferenceManager): IAppLaunch {
    override fun getLaunchTimes(): Int {
        return sharedPreferenceManager.getLaunchTimes()
    }

    override fun updateLaunchTimes(count: Int) {
        sharedPreferenceManager.changeLaunchCounter(count)
    }
}