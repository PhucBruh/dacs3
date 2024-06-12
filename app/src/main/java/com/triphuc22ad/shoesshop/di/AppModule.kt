package com.triphuc22ad.shoesshop.di

import android.content.Context
import com.triphuc22ad.shoesshop.data.service.ProductService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideAppStateRepository(productService: ProductService): AppStateRepository {
        return AppStateRepository(productService)
    }
}