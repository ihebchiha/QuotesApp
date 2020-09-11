package com.ihebchiha.hiltapp.networking.mapper

import com.ihebchiha.hiltapp.networking.response.models.QuotesResponse
import com.ihebchiha.hiltapp.networking.result.models.Quote


// Extension function that serves as a mapper (minimizing the information in the response model and putting it in another model to use within the app)
fun QuotesResponse.mapToDomain() = Quote(id, date, title.rendered, content.rendered)
