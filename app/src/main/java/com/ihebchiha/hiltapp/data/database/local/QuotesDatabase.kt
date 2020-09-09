package com.ihebchiha.hiltapp.data.database.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ihebchiha.hiltapp.networking.result.models.Quote
import dagger.Provides

/**
 * The Room Database that contains the Task table.
 *
 * Note that exportSchema should be true in production databases.
 */
@Database(entities = [Quote::class], version = 1, exportSchema = false)
abstract class QuotesDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuotesDao
}