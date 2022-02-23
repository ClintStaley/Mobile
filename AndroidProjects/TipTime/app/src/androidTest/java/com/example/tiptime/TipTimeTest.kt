package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.*

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TipTimeTest {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.tiptime", appContext.packageName)
    }

    @Test
    fun compute_tips() {
        // Do tip rounded up, and not rounded up
        val svcCost = onView(withId(R.id.service_cost_et))
        val calcTip = onView(withId(R.id.calculate_tip))
        val tipResult = onView(withId(R.id.tip_result))

        svcCost.perform(typeText("20.12\n"))
        calcTip.perform(click())
        tipResult.check(matches(withText(endsWith(" $4.00"))))
        calcTip.perform(click())
        tipResult.check(matches(withText(endsWith(" $3.02"))))

        // Round up switch, but with blank tip
        svcCost.perform(clearText())
        onView(withId(R.id.tip_round)).perform(swipeRight())
        calcTip.perform(click())
        tipResult.check(matches(withText(equalTo(""))))

        // Now do a real tip, and the round up should still be there
        svcCost.perform(typeText("123.45"))
        onView(withId(R.id.option_20)).perform(click())
        calcTip.perform(click())
        tipResult.check(matches(withText(containsString(" $25.00"))))

        svcCost.perform(clearText())
        svcCost.perform(typeText("Hello"))
        calcTip.perform(click())
        tipResult.check(matches(withText(equalTo(""))))
    }
}