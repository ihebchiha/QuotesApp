package com.ihebchiha.hiltapp.repository

import com.ihebchiha.hiltapp.data.database.local.QuotesDao
import com.ihebchiha.hiltapp.data.database.remote.QuotesRemoteDataSource
import com.ihebchiha.hiltapp.networking.mapper.mapToDomain
import com.ihebchiha.hiltapp.networking.response.models.QuotesResponse
import com.ihebchiha.hiltapp.networking.result.models.Quote
import com.ihebchiha.hiltapp.utils.extensions.Resource
import com.ihebchiha.hiltapp.utils.extensions.performGetOperation

class QuotesRepositoryImpl(
    private val quotesRemoteDataSource: QuotesRemoteDataSource,
    private val localDataSource: QuotesDao
) {
    fun getAllQuotes() = performGetOperation(
        databaseQuery = { localDataSource.getAllQuotes() },
        networkCall = { quotesRemoteDataSource.getAllQuotes() },
        saveCallResult = { 8 })

    suspend fun getAllQuotesFromApi(): Resource<List<Quote>>? {
        val res: Resource<List<QuotesResponse>> = quotesRemoteDataSource.getAllQuotes()
        if (res.status == Resource.Status.SUCCESS) {
            val o = res.data?.map { quote ->
               quote.map()
            }!!
            return Resource.success(o)
        } else if (res.status == Resource.Status.ERROR) {
            return Resource.error(message = "error has occurred")
        }
        return null
    }
}
