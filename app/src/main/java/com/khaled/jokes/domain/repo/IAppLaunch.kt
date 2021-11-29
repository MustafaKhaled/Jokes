package com.khaled.jokes.domain.repo

interface IAppLaunch {
    fun getLaunchTimes(): Int
    fun updateLaunchTimes(count: Int)
}