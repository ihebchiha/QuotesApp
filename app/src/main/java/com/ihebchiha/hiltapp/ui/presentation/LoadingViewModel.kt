package com.ihebchiha.hiltapp.ui.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.ihebchiha.hiltapp.repository.LoadingRepository

class LoadingViewModel @ViewModelInject constructor(private val repo: LoadingRepository) : ViewModel(){
}