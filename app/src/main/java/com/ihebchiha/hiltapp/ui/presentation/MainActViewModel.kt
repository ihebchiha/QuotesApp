package com.ihebchiha.hiltapp.ui.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.ihebchiha.hiltapp.repository.QuotesRepositoryImpl

class MainActViewModel @ViewModelInject constructor(private val quotesRepository: QuotesRepositoryImpl) : ViewModel() {
}