package dev.jayvijaygohil.nbateamsviewer.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dev.jayvijaygohil.nbateamsviewer.common.*
import dev.jayvijaygohil.nbateamsviewer.domain.data.network.ScoreServerGateway
import dev.jayvijaygohil.nbateamsviewer.domain.data.network.ScoreServerGateway.Companion.CACHE_CHILD_PATH
import dev.jayvijaygohil.nbateamsviewer.domain.data.network.ScoreServerGateway.Companion.PROD_BASE_URL
import dev.jayvijaygohil.nbateamsviewer.domain.data.network.getNetworkCache
import dev.jayvijaygohil.nbateamsviewer.domain.data.network.getOkHttpClient
import dev.jayvijaygohil.nbateamsviewer.domain.data.network.getRetrofit
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
class ApplicationModule {

    @Provides
    @ApplicationScope
    fun provideScoreServerGateway(retrofit: Retrofit): ScoreServerGateway {
        return retrofit.create(ScoreServerGateway::class.java)
    }

    @Provides
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
    @ApplicationScope
    @MainThreadScheduler
    fun provideMainThreadScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @ApplicationScope
    @IoScheduler
    fun provideIoScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun provideApplicationContext(application: Application): Context {
        return application
    }
}