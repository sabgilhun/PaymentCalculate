package com.example.socarpaymentcalculate.common

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.setNetworkingThread(): Single<T> =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <T> Maybe<T>.setNetworkingThread(): Maybe<T> =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.setNetworkingThread(): Observable<T> =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

inline fun <T, reified E : T> Observable<T>.filterTo(
    @Suppress("UNUSED_PARAMETER") target: Class<E>
): Observable<E> = this.filter {
    when (it) {
        is E -> true
        else -> false
    }
}.map { it as E }
