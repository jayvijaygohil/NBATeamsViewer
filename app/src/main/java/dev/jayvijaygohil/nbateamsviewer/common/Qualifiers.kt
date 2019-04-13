package dev.jayvijaygohil.nbateamsviewer.common

import javax.inject.Qualifier

@Qualifier
annotation class ApplicationContext

@Qualifier
annotation class ProdServerUrl

@Qualifier
annotation class NetworkCacheChildPath

@Qualifier
annotation class NetworkCache

@Qualifier
annotation class MainThreadScheduler

@Qualifier
annotation class IoScheduler