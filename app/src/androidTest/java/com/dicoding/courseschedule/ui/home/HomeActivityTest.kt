package com.dicoding.courseschedule.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dicoding.courseschedule.R
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun when_tap_Add_Course_the_AddCourseActivity_displayed() {
        onView(withId(R.id.action_add)).check(matches(isDisplayed()))
        onView(withId(R.id.action_add)).perform(click())
        onView(withId(R.id.ed_course_name)).check(matches(isDisplayed()))

        onView(withId(R.id.spinner_day)).check(matches(isDisplayed()))

        onView(withId(R.id.ib_start_time)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_start_time)).check(matches(isDisplayed()))
        onView(withId(R.id.add_tv_startTime)).check(matches(isDisplayed()))

        onView(withId(R.id.ib_end_time)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_end_time)).check(matches(isDisplayed()))
        onView(withId(R.id.add_tv_endTime)).check(matches(isDisplayed()))

        onView(withId(R.id.ed_lecturer)).check(matches(isDisplayed()))
        onView(withId(R.id.ed_note)).check(matches(isDisplayed()))
    }

}