package com.ihebchiha.hiltapp.data.database.local

import com.ihebchiha.hiltapp.data.database.QuotesDataSource
import com.ihebchiha.hiltapp.networking.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuotesLocalDataSource @Inject constructor(private val quotesDao: QuotesDao){

}