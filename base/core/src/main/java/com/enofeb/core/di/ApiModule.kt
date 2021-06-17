package com.enofeb.core.di

import com.enofeb.core.constants.ApiConstants
import com.enofeb.core.constants.ApiConstants.Companion.API_KEY
import com.enofeb.core.constants.ApiConstants.Companion.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            addInterceptor(Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.header("CB-ACCESS-KEY", API_KEY)
                return@Interceptor chain.proceed(builder.build())
            })
            connectTimeout(ApiConstants.TIMEOUT_MILLISECOND, TimeUnit.MILLISECONDS)
            readTimeout(ApiConstants.TIMEOUT_MILLISECOND, TimeUnit.MILLISECONDS)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit =
        with(Retrofit.Builder()) {
            baseUrl(BASE_URL)
            client(client)
            addConverterFactory(GsonConverterFactory.create(gson))
            build()
        }

}