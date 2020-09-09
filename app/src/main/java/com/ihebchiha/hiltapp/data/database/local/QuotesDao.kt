package com.ihebchiha.hiltapp.data.database.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ihebchiha.hiltapp.networking.result.models.Quote

@Dao
interface QuotesDao {
    @Query("Select * from quote")
    fun getAllQuotes():LiveData<List<Quote>>

    @Query("Select * from quote where author = :author")
    fun getQuoteByQuotee(author: String): List<Quote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(quote: Quote)

    @Query("Delete from quote where id= :ref")
    fun deleteQuote(ref: Int)
}