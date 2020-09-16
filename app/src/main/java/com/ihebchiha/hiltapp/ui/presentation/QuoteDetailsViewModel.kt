package com.ihebchiha.hiltapp.ui.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.ihebchiha.hiltapp.networking.result.models.Quote
import com.ihebchiha.hiltapp.repository.QuotesRepositoryImpl


class QuoteDetailsViewModel @ViewModelInject constructor(private val repositoryImpl: QuotesRepositoryImpl) :
    ViewModel() {
    private val _id = MutableLiveData<Int>()
    fun start(id: Int) {
        _id.value = id
    }

    private val _quote: LiveData<Quote> = _id.switchMap { id ->
            repositoryImpl.getQuoteFromDatabase(id)
    }
    val quote = _quote

}