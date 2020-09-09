package com.ihebchiha.hiltapp.di.modules

import android.content.Context
import androidx.room.Room
import com.ihebchiha.hiltapp.data.database.local.QuotesDao
import com.ihebchiha.hiltapp.data.database.local.QuotesDatabase
import com.ihebchiha.hiltapp.data.database.remote.QuotesRemoteDataSource
import com.ihebchiha.hiltapp.networking.service.QuotesApiService
import com.ihebchiha.hiltapp.repository.QuotesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideQuotesRemoteDataSource(quotesApiService: QuotesApiService): QuotesRemoteDataSource = QuotesRemoteDataSource(quotesApiService)


    //injecting or providing the room instance to the app
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): QuotesDatabase {
        return Room.databaseBuilder(context, QuotesDatabase::class.java, "Quotes").build()
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO


    @Singleton
    @Provides
    fun provideCharacterDao(db: QuotesDatabase) = db.quoteDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: QuotesRemoteDataSource, localDataSource: QuotesDao) = QuotesRepositoryImpl(remoteDataSource, localDataSource)

}