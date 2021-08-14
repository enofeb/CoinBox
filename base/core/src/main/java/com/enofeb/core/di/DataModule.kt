package com.enofeb.core.di

import com.enofeb.core.domain.market.MarketRepository
import com.enofeb.core.domain.market.MarketRepositoryImpl
import com.enofeb.core.domain.price.PriceRepository
import com.enofeb.core.domain.price.PriceRepositoryImpl
import com.enofeb.core.service.market.MarketService
import com.enofeb.core.service.price.PriceService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providePriceServices(retrofit: Retrofit): PriceService =
        retrofit.create(PriceService::class.java)

    @Provides
    @Singleton
    fun provideMarketServices(retrofit: Retrofit): MarketService =
        retrofit.create(MarketService::class.java)

    @Provides
    @Singleton
    fun providePriceRepository(priceService: PriceService): PriceRepository =
        PriceRepositoryImpl(priceService)

    @Provides
    @Singleton
    fun provideMarketRepository(marketService: MarketService): MarketRepository =
        MarketRepositoryImpl(marketService)
}