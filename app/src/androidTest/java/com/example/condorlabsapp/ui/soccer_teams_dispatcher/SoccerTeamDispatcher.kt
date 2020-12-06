package com.example.condorlabsapp.ui.soccer_teams_dispatcher

import com.example.condorlabsapp.BaseDispatcher
import com.example.condorlabsapp.ui.TestHelper
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class SoccerTeamDispatcher : BaseDispatcher() {

    override fun dispatch(request: RecordedRequest?): MockResponse {

        if (request == null) return MockResponse().setResponseCode(404)

        if (request.path == "/api/v1/json/1/search_all_teams.php?s=Soccer&c=Spain") {
            return getResponse(TestHelper.FILE_SOCCER_TEAMS)
        }

        return MockResponse().setResponseCode(404)
    }
}