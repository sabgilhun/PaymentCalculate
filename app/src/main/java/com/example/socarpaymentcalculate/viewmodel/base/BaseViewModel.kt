package com.example.socarpaymentcalculate.viewmodel.base

import androidx.lifecycle.ViewModel
import com.example.socarpaymentcalculate.common.Action
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

abstract class BaseViewModel : ViewModel() {

    protected val actionStream = PublishSubject.create<Action>()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _errorMessage = PublishSubject.create<String>()
    val errorMessage: Observable<String>
        get() = _errorMessage

    protected fun Disposable.track() {
        compositeDisposable.add(this)
    }

    protected fun handleRemoteError(throwable: Throwable) {
        throwable.printStackTrace()
        _errorMessage.onNext("서버에서 데이터를 가져오는데에 실패하였습니다.")
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun <T : Action> flowAction(action: T) {
        actionStream.onNext(action)
    }
}