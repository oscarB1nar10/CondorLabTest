package com.example.condorlabsapp.ui

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.condorlabsapp.R
import com.example.condorlabsapp.framework.presentation.MainActivity
import com.example.condorlabsapp.ui.TestHelper.MOCK_SERVER_PORT
import com.example.condorlabsapp.ui.soccer_teams_dispatcher.SoccerTeamDispatcher
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, false, true)

    private var mockWebServer = MockWebServer()

    @Before
    fun init() {
        Intents.init()

        mockWebServer.start(MOCK_SERVER_PORT)
        mockWebServer.setDispatcher(SoccerTeamDispatcher())
        hiltRule.inject()

        activityRule.launchActivity(Intent())
    }

    @Test
    fun testSoccerListContent(){
        onView(withId(R.id.rcv_soccer_teams)).check(TestHelper.RecyclerViewItemCountAssertion(4))
    }

    @After
    fun tearDown() {

        mockWebServer.shutdown()

        Intents.release()
    }
}