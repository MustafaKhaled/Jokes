package com.khaled.jokes

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.khaled.jokes.ui.SplashScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith




@RunWith(AndroidJUnit4::class)
@LargeTest
class SplashScreenTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<SplashScreen>
            = ActivityScenarioRule(SplashScreen::class.java)

    @Test
    fun checkSplashScreenViews_appeared(){
        onView(ViewMatchers.withId(R.id.splashScreenImage))
        onView(ViewMatchers.withId(R.id.progressBar))
        onView(ViewMatchers.withId(R.id.launchTimesTextView))
    }
}