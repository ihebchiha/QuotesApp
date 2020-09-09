package com.ihebchiha.hiltapp.data.database

import com.ihebchiha.hiltapp.networking.response.models.QuotesResponse
import retrofit2.Response

interface QuotesDataSource {
    suspend fun getAllQuotes() : Response<List<QuotesResponse>>
}