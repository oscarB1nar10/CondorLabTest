package com.example.condorlabsapp.ui

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.condorlabsapp.R
import com.example.condorlabsapp.di.AppModule
import com.example.condorlabsapp.framework.presentation.MainActivity
import com.example.condorlabsapp.ui.TestHelper.MOCK_SERVER_PORT
import com.example.condorlabsapp.ui.soccer_teams_dispatcher.SoccerTeamDispatcher
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@ExperimentalCoroutinesApi
@UninstallModules(AppModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    private var mockWebServer = MockWebServer()

    @Inject
    lateinit var okHttp: OkHttpClient

    @Before
    fun setUp() {
        hiltRule.inject()

        mockWebServer.start(MOCK_SERVER_PORT)
        mockWebServer.setDispatcher(SoccerTeamDispatcher())

        activityRule.launchActivity(Intent())
    }

    @Test
    fun testSoccerListContent() {

        onView(withId(R.id.rcv_soccer_teams)).check(TestHelper.RecyclerViewItemCountAssertion(4))
    }

    @After
    fun tearDown() =
        mockWebServer.shutdown()

}