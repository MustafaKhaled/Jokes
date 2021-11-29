package com.khaled.jokes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.khaled.jokes.ui.JokesActivity
import com.khaled.jokes.util.CountingIdlingResourceSingleton
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class JokesActivityTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<JokesActivity> =
        ActivityScenarioRule(JokesActivity::class.java)
    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @Test
    fun checkSwipeRefreshViews_appeared() {
        onView(withId(R.id.swipeRefresh))
            .check(matches(isDisplayed()))
    }
    @Test
    fun checkSwipeRefresh_hide() {
        if (CountingIdlingResourceSingleton.countingIdlingResource.isIdleNow) {
            onView(withId(R.id.swipeRefresh)).check(matches(not(isDisplayed())))
        }
    }

    @Test
    fun swipeToRefresh_shouldShowProgress() {
        onView(withId(R.id.swipeRefresh))
            .perform(ViewActions.swipeDown()).check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(CountingIdlingResourceSingleton.countingIdlingResource)
    }
}
