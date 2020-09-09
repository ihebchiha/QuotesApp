package com.ihebchiha.hiltapp.ui.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ihebchiha.hiltapp.Event
import com.ihebchiha.hiltapp.networking.mapper.QuoteMapper
import com.ihebchiha.hiltapp.networking.result.Result
import com.ihebchiha.hiltapp.networking.result.models.Quote
import com.ihebchiha.hiltapp.repository.QuotesRepository
import com.ihebchiha.hiltapp.repository.QuotesRepositoryImpl
import com.ihebchiha.hiltapp.utils.extensions.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActViewModel @ViewModelInject constructor(private val quotesRepository: QuotesRepositoryImpl) : ViewModel() {
}