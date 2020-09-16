package com.ihebchiha.hiltapp.data.database.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ihebchiha.hiltapp.networking.result.models.Quote

@Dao
interface QuotesDao {
    @Query("Select * from quotes")
    fun getAllQuotes():LiveData<List<Quote>>

    @Query("Select * from quotes where author = :author")
    fun getQuoteByQuotee(author: String): List<Quote>

    @Query("Select * from quotes where id = :id")
    fun getQuoteByID(id: Int): LiveData<Quote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(quote: Quote)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTasks(quotes: List<Quote>)

    @Query("Delete from quotes where id= :ref")
    fun deleteQuote(ref: Int)

    @Delete
    suspend fun deleteAllQuotes(quotes: List<Quote>)
}