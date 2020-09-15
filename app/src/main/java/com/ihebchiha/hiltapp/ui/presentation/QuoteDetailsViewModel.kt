package com.ihebchiha.hiltapp.ui.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ihebchiha.hiltapp.networking.result.models.Quote
import com.ihebchiha.hiltapp.repository.QuotesRepositoryImpl
import kotlinx.coroutines.launch


class QuoteDetailsViewModel @ViewModelInject constructor(private val repositoryImpl: QuotesRepositoryImpl): ViewModel() {
    var quoteId = 0
    private val _quote: LiveData<Quote> = liveData {  viewModelScope.launch { repositoryImpl.getQuoteFromDatabase(quoteId) }}
    val quote = _quote
}