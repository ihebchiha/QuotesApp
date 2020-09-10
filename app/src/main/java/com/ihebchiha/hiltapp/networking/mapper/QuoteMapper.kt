package com.ihebchiha.hiltapp.networking.mapper

import com.ihebchiha.hiltapp.networking.response.models.QuotesResponse
import com.ihebchiha.hiltapp.networking.result.models.Quote


fun QuotesResponse.mapToDomain() = Quote(id, date, title.rendered, content.rendered)
