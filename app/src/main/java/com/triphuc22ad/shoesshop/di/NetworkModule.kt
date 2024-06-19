package com.triphuc22ad.shoesshop.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.triphuc22ad.shoesshop.data.TokenManager
import com.triphuc22ad.shoesshop.data.service.AnalysisService
import com.triphuc22ad.shoesshop.data.service.AuthService
import com.triphuc22ad.shoesshop.data.service.BrandService
import com.triphuc22ad.shoesshop.data.service.InventoryService
import com.triphuc22ad.shoesshop.data.service.OrderService
import com.triphuc22ad.shoesshop.data.service.ProductService
import com.triphuc22ad.shoesshop.data.service.SpecialOfferService
import com.triphuc22ad.shoesshop.data.service.UserService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val BASE_URL = "http://192.168.1.8:8080/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        tokenManager: TokenManager,
        appStateRepository: AppStateRepository
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val token = runBlocking { tokenManager.token.first() }
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            val response = chain.proceed(request)
            if (response.code == 401) {
                appStateRepository.resetAppState()
                appStateRepository.updateNotify("Please login again")
            }
            response
        }.build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun providesAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Singleton
    @Provides
    fun providesUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Singleton
    @Provides
    fun providesProductService(retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }

    @Singleton
    @Provides
    fun providesBrandService(retrofit: Retrofit): BrandService {
        return retrofit.create(BrandService::class.java)
    }

    @Singleton
    @Provides
    fun providesSpecialOfferService(retrofit: Retrofit): SpecialOfferService {
        return retrofit.create(SpecialOfferService::class.java)
    }

    @Singleton
    @Provides
    fun providesInventoryService(retrofit: Retrofit): InventoryService {
        return retrofit.create(InventoryService::class.java)
    }

    @Singleton
    @Provides
    fun providesOrderService(retrofit: Retrofit): OrderService {
        return retrofit.create(OrderService::class.java)
    }

    @Singleton
    @Provides
    fun analysisService(retrofit: Retrofit): AnalysisService {
        return retrofit.create(AnalysisService::class.java)
    }
}