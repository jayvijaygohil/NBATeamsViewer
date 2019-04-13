package dev.jayvijaygohil.nbateamsviewer.application.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dev.jayvijaygohil.nbateamsviewer.common.*
import dev.jayvijaygohil.nbateamsviewer.data.network.ScoreServerGateway
import dev.jayvijaygohil.nbateamsviewer.data.network.ScoreServerGateway.Companion.CACHE_CHILD_PATH
import dev.jayvijaygohil.nbateamsviewer.data.network.ScoreServerGateway.Companion.PROD_BASE_URL
import dev.jayvijaygohil.nbateamsviewer.data.network.getNetworkCache
import dev.jayvijaygohil.nbateamsviewer.data.network.getOkHttpClient
import dev.jayvijaygohil.nbateamsviewer.data.network.getRetrofit
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @ApplicationScope
    fun provideScoreServerGateway(retrofit: Retrofit): ScoreServerGateway {
        return retrofit.create(ScoreServerGateway::class.java)
    }

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
    @NetworkCacheChildPath
    fun provideNetworkCacheChildPath(): String {
        return CACHE_CHILD_PATH
    }

    @Provides
    @ProdServerUrl
    fun provideServerUrl(): String {
        return PROD_BASE_URL
    }

    @Provides
    @ApplicationContext
    fun provideApplicationContext(application: Application): Context {
        return application
    }

    @Provides
    @MainThreadScheduler
    fun provideMainThreadScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @IoScheduler
    fun provideIoScheduler(): Scheduler {
        return Schedulers.io()
    }
}