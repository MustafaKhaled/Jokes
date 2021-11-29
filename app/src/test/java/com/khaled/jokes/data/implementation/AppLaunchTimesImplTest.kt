package com.khaled.jokes.data.implementation

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.khaled.jokes.data.local.SharedPreferenceManager
import com.khaled.jokes.data.local.launchCounterKey
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class AppLaunchTimesImplTest {

    lateinit var sharedPreferenceManager: SharedPreferenceManager
    private val context = mockk<Context>(relaxed = true)
    private lateinit var sharedPreferences: SharedPreferences
    @Before
    fun setUp() {
        sharedPreferences = context.getSharedPreferences(
            "prefs",
            MODE_PRIVATE
        )
        sharedPreferenceManager = SharedPreferenceManager(sharedPreferences)
    }

    @Test
    fun test_get_launch_times_should_Return_saved_counter() {
        val expected = 1
        val launchTimesImpl = AppLaunchTimesImpl(sharedPreferenceManager)
        every { sharedPreferenceManager.getLaunchTimes() } returns 1
        val result = launchTimesImpl.getLaunchTimes()
        assertEquals(expected, result)
    }

    @Test
    fun verify_shared_preferences_get_when_getLaunchTimes_called() {
        val launchTimesImpl = AppLaunchTimesImpl(sharedPreferenceManager)
        every { sharedPreferenceManager.getLaunchTimes() } returns 1
        launchTimesImpl.getLaunchTimes()
        verify { sharedPreferences.getInt(launchCounterKey, any()) }
    }

    @Test
    fun verify_shared_preferences_edit_when_update_counter() {
        val launchTimesImpl = AppLaunchTimesImpl(sharedPreferenceManager)
        launchTimesImpl.updateLaunchTimes(2)
        verify { sharedPreferences.edit().putInt(launchCounterKey, 2).apply() }
    }
}
