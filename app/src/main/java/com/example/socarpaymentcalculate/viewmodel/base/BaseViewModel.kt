package com.example.socarpaymentcalculate.viewmodel.base

import androidx.lifecycle.ViewModel
import com.example.socarpaymentcalculate.common.Action
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

abstract class BaseViewModel : ViewModel() {

    protected val actionStream = PublishSubject.create<Action>()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun Disposable.track() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun <T : Action> flowAction(action: T) {
        actionStream.onNext(action)
    }
}