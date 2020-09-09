package com.ihebchiha.hiltapp.networking.service

import com.ihebchiha.hiltapp.networking.params.AllQUOTES
import com.ihebchiha.hiltapp.networking.response.models.QuotesResponse
import retrofit2.Response
import retrofit2.http.GET

interface QuotesApiService {

    @GET(AllQUOTES)
    suspend fun getAllQuotes(): Response<List<QuotesResponse>>
}