package com.example.socarpaymentcalculate.viewmodel.search

import com.example.socarpaymentcalculate.common.filterTo
import com.example.socarpaymentcalculate.data.TmapRepository
import com.example.socarpaymentcalculate.data.model.Poi
import com.example.socarpaymentcalculate.viewmodel.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class SearchViewModel(private val repository: TmapRepository) : BaseViewModel() {

    private var keyword: String = ""

    private var _searchedPois = PublishSubject.create<List<Poi>>()
    val searchedPois: Observable<List<Poi>>
        get() = _searchedPois.hide()

    init {
        actionStream
            .filterTo(SearchKeywordChangeAction::class.java)
            .map { it.keyword }
            .subscribe { keyword = it }
            .track()

        actionStream
            .filterTo(SearchBottunClickAction::class.java)
            .subscribe { searchPois() }
            .track()
    }

    private fun searchPois() {
        if (!keyword.isBlank()) {
            repository.getPois(keyword, {
                _searchedPois.onNext(it)
            }, {}).track()
        }
    }
}