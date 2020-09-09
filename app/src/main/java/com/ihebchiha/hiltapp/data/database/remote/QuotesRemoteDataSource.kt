package com.ihebchiha.hiltapp.data.database.remote

import com.ihebchiha.hiltapp.base.BaseDataSource
import com.ihebchiha.hiltapp.networking.response.models.QuotesResponse
import com.ihebchiha.hiltapp.networking.service.QuotesApiService
import com.ihebchiha.hiltapp.utils.extensions.Resource
import javax.inject.Inject


class QuotesRemoteDataSource @Inject constructor(private val quotesApi: QuotesApiService) :
    BaseDataSource() {

    suspend fun getAllQuotes(): Resource<List<QuotesResponse>> =
        getResult { quotesApi.getAllQuotes() }
}