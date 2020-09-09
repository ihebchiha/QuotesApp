package com.ihebchiha.hiltapp.ui.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ihebchiha.hiltapp.Event
import com.ihebchiha.hiltapp.networking.result.models.Quote
import com.ihebchiha.hiltapp.repository.QuotesRepositoryImpl
import com.ihebchiha.hiltapp.utils.extensions.Resource
import kotlinx.coroutines.launch

class QuotesViewModel @ViewModelInject constructor(private val repo: QuotesRepositoryImpl): ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage = _errorMessage
    private val _forceUpdate = MutableLiveData<Boolean>(false)
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading = _dataLoading
    private val _items: LiveData<Resource<List<Quote>>> = repo.getAllQuotes()
    private val _qoutes = MutableLiveData<Resource<List<Quote>>>()
    val qoutes : LiveData<Resource<List<Quote>>> = _qoutes

     fun getQuotes(){
        viewModelScope.launch{
            _qoutes.postValue(repo.getAllQuotesFromApi())
        }
    }

    val items = _items
    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText
}