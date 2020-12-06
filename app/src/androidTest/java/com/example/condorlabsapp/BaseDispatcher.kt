package com.example.condorlabsapp

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import util.ResourceUtil

open class BaseDispatcher : Dispatcher() {

    protected fun dispatchNotFound() = MockResponse().setResponseCode(404).setBody(
        "{message: \"Not Found\"}"
    )

    // generic success response
    protected fun successResponse(resourceId: Int) = MockResponse().setResponseCode(200).setBody(
        "{\"Response\": $resourceId, \"Message\": \"success\"}"
    )


    fun getResponse(filename: String): MockResponse {
        return MockResponse().setResponseCode(200).setBody(ResourceUtil.readResponseFromFile(javaClass.classLoader, filename))
    }

    override fun dispatch(request: RecordedRequest?): MockResponse {
        return dispatchNotFound()
    }
}