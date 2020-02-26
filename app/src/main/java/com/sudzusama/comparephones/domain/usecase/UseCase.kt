package com.sudzusama.comparephones.domain.usecase

import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

abstract class UseCase<T>(
    private val subscribeScheduler: Scheduler,
    private val postExecutionScheduler: Scheduler
) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun subscribe(disposableSubscriber: DisposableSubscriber<T>) {
        val flowable: Flowable<T> =
            createUseCase()
                .subscribeOn(subscribeScheduler)
                .observeOn(postExecutionScheduler)
        compositeDisposable.add(flowable.subscribeWith(disposableSubscriber))
    }

    fun subscribe(onNextFunc: (T) -> Unit, onErrorFunc: (Throwable) -> Unit) {
        val flowable: Flowable<T> =
            createUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        compositeDisposable.add(flowable.subscribeWith(object : DisposableSubscriber<T>() {
            override fun onComplete() {}
            override fun onNext(t: T) {
                onNextFunc(t)
            }

            override fun onError(t: Throwable) {
                onErrorFunc(t)
            }
        }))
    }

    fun dispose() {
        compositeDisposable.clear()
    }

    protected abstract fun createUseCase(): Flowable<T>
}