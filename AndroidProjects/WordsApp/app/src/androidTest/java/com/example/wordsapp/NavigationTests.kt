package com.example.wordsapp

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTests {

    @Test
    fun navigateToWords() {
        val navController = TestNavHostController(
         ApplicationProvider.getApplicationContext())

        val letterScenario = launchFragmentInContainer<LetterFragment>(
            themeResId = R.style.Theme_Words)

        letterScenario.onFragment {frag ->
            navController.setGraph(R.navigation.nav)
            Navigation.setViewNavController(frag.requireView(), navController)
        }

        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.
             actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click())
        )

        assertEquals(navController.currentDestination?.id, R.id.wordFragment)
    }

    @Test
    fun pickWords() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val args = Bundle()

        args.putString(WordFragment.LETTER, "B")

        val wordScenario = launchFragmentInContainer<WordFragment>(
            fragmentArgs = args,
            themeResId = R.style.Theme_Words
        )

        wordScenario.onFragment {frag ->
            navController.setGraph(R.navigation.nav)
            Navigation.setViewNavController(frag.requireView(), navController)
        }

        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.
            actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click())
        )

        assertEquals(navController.currentDestination?.id, R.id.wordFragment)
    }
}