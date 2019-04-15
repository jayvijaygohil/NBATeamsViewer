package dev.jayvijaygohil.nbateamsviewer.domain.data.network

import android.content.Context
import dev.jayvijaygohil.nbateamsviewer.domain.entity.Team
import io.reactivex.Single
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import java.io.File

interface ScoreServerGateway {
    @Headers(CACHE_HEADER)
    @GET("scoremedia/nba-team-viewer/master/input.json")
    fun getTeamList(): Single<List<Team>>

    companion object {
        private const val ONE_DAY_IN_SECONDS = 24L * 60L * 60L
        // max-stale indicates that the client is willing to accept a response that has exceeded its freshness lifetime
        // by no more than the specified number of seconds.
        const val CACHE_HEADER = "cache-control: max-stale=$ONE_DAY_IN_SECONDS"
        const val PROD_BASE_URL = "https://raw.githubusercontent.com/"
        const val CACHE_CHILD_PATH = "teamListCache"
    }
}

fun getNetworkCache(context: Context, networkCacheChildPath: String): Cache {
    val httpCacheDirectory = File(context.cacheDir, networkCacheChildPath)
    val cacheSize = (2 * 1024 * 1024).toLong() // Our cache will be of 2 MB

    return Cache(httpCacheDirectory, cacheSize)
}

fun getOkHttpClient(cache: Cache): OkHttpClient {
    return OkHttpClient
        .Builder()
        .cache(cache)
        .build()
}

fun getRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}