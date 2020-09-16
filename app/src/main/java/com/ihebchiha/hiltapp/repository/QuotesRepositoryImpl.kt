package com.ihebchiha.hiltapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.ihebchiha.hiltapp.data.database.local.QuotesDao
import com.ihebchiha.hiltapp.data.database.remote.QuotesRemoteDataSource
import com.ihebchiha.hiltapp.networking.response.models.QuotesResponse
import com.ihebchiha.hiltapp.networking.result.models.Quote
import com.ihebchiha.hiltapp.utils.extensions.Resource
import com.ihebchiha.hiltapp.utils.extensions.performGetOperation
import kotlinx.coroutines.Dispatchers

class QuotesRepositoryImpl(
    private val quotesRemoteDataSource: QuotesRemoteDataSource,
    private val localDataSource: QuotesDao
) {
    fun getAllQuotes() = performGetOperation(
        databaseQuery = { localDataSource.getAllQuotes() },
        networkCall = { quotesRemoteDataSource.getAllQuotes() },
        saveCallResult = { localDataSource.insertAllTasks(getAllQuotesFromApi()?.data!!) })

    suspend fun getAllQuotesFromApi(): Resource<List<Quote>>? {
        val res: Resource<List<QuotesResponse>> = quotesRemoteDataSource.getAllQuotes()
        if (res.status == Resource.Status.SUCCESS) {
            val o = res.data?.map { quote ->
                quote.map()

            }!!
            //clearing the table to free some space for another records "Quotes"
           // localDataSource.deleteAllQuotes(localDataSource.getAllQuotes().value!!)
            localDataSource.insertAllTasks(o)
            return Resource.success(o)
        } else if (res.status == Resource.Status.ERROR) {
            return Resource.error(message = "error has occurred")
        }
        return null
    }

    fun getQuoteFromDatabase(id: Int): LiveData<Quote> {
        return liveData(Dispatchers.IO) {
            localDataSource.getQuoteByID(id = id)
        }
    }
}
