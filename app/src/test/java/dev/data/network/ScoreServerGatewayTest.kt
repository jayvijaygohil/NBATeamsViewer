package dev.data.network

import dev.jayvijaygohil.nbateamsviewer.data.network.ScoreServerGateway
import dev.jayvijaygohil.nbateamsviewer.data.network.ScoreServerGateway.Companion.CACHE_HEADER
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ScoreServerGatewayTest {
    private lateinit var server: MockWebServer
    private lateinit var gateway: ScoreServerGateway

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("/").toString())
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        gateway = retrofit.create(ScoreServerGateway::class.java)
    }

    @After
    fun cleanUp() {
        server.shutdown()
    }

    @Test
    fun test_CACHE_HEADER_IS_INCLUDED_IN_THE_REQUEST() {
        val cacheHeaderKey = "cache-control"
        val expectedCacheHeaderValue = "max-stale=86400"

        server.enqueue(MockResponse().setResponseCode(200))
        gateway.getTeamList().test().await(5, TimeUnit.SECONDS)

        val request = server.takeRequest()
        val actualCacheHeaderValue = request.getHeader(cacheHeaderKey)

        Assert.assertTrue(actualCacheHeaderValue != null)
        Assert.assertEquals(expectedCacheHeaderValue, actualCacheHeaderValue)
    }
}