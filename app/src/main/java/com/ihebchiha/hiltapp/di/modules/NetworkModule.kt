package com.ihebchiha.hiltapp.di.modules

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.ihebchiha.hiltapp.networking.params.BASE_URL
import com.ihebchiha.hiltapp.networking.service.QuotesApiService
import com.ihebchiha.hiltapp.networking.utils.HttpRequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule{

    @Singleton
    @Provides
    fun providesOkhttpCache(@ApplicationContext context: Context): Cache {
        return Cache(context.cacheDir, 10 * 1024 * 1024)
    }

    @Provides
    fun providesOkHttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
            .cache(cache)
            .addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10.toLong(), TimeUnit.SECONDS)
            .readTimeout(10.toLong(), TimeUnit.SECONDS)
            .cache(cache)
        return client.build()
    }

    @Singleton
    @Provides
    fun providesHTTPClient(okHttpClient: OkHttpClient): Retrofit {
        val gsonBuilder = GsonBuilder()
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .client(okHttpClient).build()
    }

    @Provides
    fun providesApiService(retrofit: Retrofit): QuotesApiService {
        return retrofit.create(QuotesApiService::class.java)
    }
}