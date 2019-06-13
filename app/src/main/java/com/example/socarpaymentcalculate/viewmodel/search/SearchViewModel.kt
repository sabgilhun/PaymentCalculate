package com.example.socarpaymentcalculate.viewmodel.search

import com.example.socarpaymentcalculate.common.filterTo
import com.example.socarpaymentcalculate.common.setNetworkingThread
import com.example.socarpaymentcalculate.data.TmapRepository
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.viewmodel.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

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
            .subscribe { searchPois() }
            .track()
    }

    private fun searchPois() {
        if (!keyword.isBlank()) {
            repository.getPois(keyword)
                .setNetworkingThread()
                .subscribe(
                    {
                        _searchedPois.onNext(it)
                    },
                    ::handleRemoteError
                )
                .track()
        }
    }
}