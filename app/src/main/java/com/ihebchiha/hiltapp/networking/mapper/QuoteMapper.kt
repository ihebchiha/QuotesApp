package com.ihebchiha.hiltapp.networking.mapper

import com.ihebchiha.hiltapp.networking.response.models.QuotesResponse
import com.ihebchiha.hiltapp.networking.result.models.Quote

object QuoteMapper {

    fun map(quotesResponse: QuotesResponse): Quote {
        var quote: Quote
        quotesResponse.let {
            quote = Quote(it.id, it.date, it.title.rendered, it.content.rendered)
        }
        return quote
    }
}


fun QuotesResponse.mapToDomain() = Quote(id, date, title.rendered, content.rendered)
