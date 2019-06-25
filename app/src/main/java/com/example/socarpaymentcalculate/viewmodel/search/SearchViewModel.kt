package com.example.socarpaymentcalculate.viewmodel.search

import com.example.socarpaymentcalculate.common.filterTo
import com.example.socarpaymentcalculate.common.setNetworkingThread
import com.example.socarpaymentcalculate.data.TmapRepository
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.viewmodel.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SearchViewModel(private val repository: TmapRepository) : BaseViewModel() {

    private var keyword: String = ""

    private val _searchedPois = PublishSubject.create<List<Poi>>()
    val searchedPois: Observable<List<Poi>>
        get() = _searchedPois

    init {
        actionStream
            .filterTo(SearchKeywordChangeAction::class.java)
            .map { it.keyword }
            .subscribe { keyword = it }
            .track()

        actionStream
            .filterTo(SearchButtonClickAction::class.java)
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe { searchPois() }
            .track()
    }

    private fun searchPois() {
        if (!keyword.isBlank()) {
            startLoading()
            repository.getPois(keyword)
                .toObservable()
                .setNetworkingThread()
                .subscribe {
                    endLoading()
                    if (it.isNotEmpty()) {
                        _searchedPois.onNext(it.get())
                    } else {
                        handleRemoteError(it.getError())
                    }
                }
                .track()
        }
    }
}