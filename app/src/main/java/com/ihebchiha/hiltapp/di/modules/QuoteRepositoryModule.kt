package com.ihebchiha.hiltapp.di.modules

import com.ihebchiha.hiltapp.data.database.QuotesDataSource
import com.ihebchiha.hiltapp.repository.QuotesRepository
import com.ihebchiha.hiltapp.repository.QuotesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton
