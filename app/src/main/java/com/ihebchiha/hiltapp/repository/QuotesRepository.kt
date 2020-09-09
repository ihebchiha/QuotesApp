package com.ihebchiha.hiltapp.repository

import com.ihebchiha.hiltapp.networking.response.models.QuotesResponse
import com.ihebchiha.hiltapp.networking.result.Result

interface QuotesRepository {
    suspend fun getAllQuotes(): Result<List<QuotesResponse>>
}