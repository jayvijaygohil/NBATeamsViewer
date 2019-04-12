package dev.jayvijaygohil.nbateamsviewer.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dev.jayvijaygohil.nbateamsviewer.data.network.*
import dev.jayvijaygohil.nbateamsviewer.data.network.ScoreServerGateway.Companion.CACHE_CHILD_PATH
import dev.jayvijaygohil.nbateamsviewer.data.network.ScoreServerGateway.Companion.PROD_BASE_URL
import dev.jayvijaygohil.nbateamsviewer.ui.MainActivity
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideRetrofit(@ProdServerUrl baseUrl: String, client: OkHttpClient): Retrofit {
        return getRetrofit(baseUrl, client)
    }

    @Provides
    fun provideOkHttpClient(@NetworkCache cache: Cache): OkHttpClient {
        return getOkHttpClient(cache)
    }

    @Provides
    @NetworkCache
    fun provideNetworkCache(@ApplicationContext context: Context, @NetworkCacheChildPath networkCacheChildPath: String): Cache {
        return getNetworkCache(context, networkCacheChildPath)
    }

    @Provides
    @ProdServerUrl
    fun provideServerUrl() = PROD_BASE_URL

    @Provides
    @NetworkCacheChildPath
    fun provideNetworkCacheChildPath() = CACHE_CHILD_PATH

    @Provides
    @Singleton
    @ApplicationContext
    fun provideApplicationContext(): Context = application.applicationContext
}