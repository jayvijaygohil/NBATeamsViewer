package dev.jayvijaygohil.nbateamsviewer.common

interface BasePresenter<in T> {
    fun attachView(view: T)
    fun detachView()
}