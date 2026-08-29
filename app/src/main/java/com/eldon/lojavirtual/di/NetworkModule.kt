package com.eldon.lojavirtual.di

import com.eldon.lojavirtual.data.remote.ProductsApiService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.eldon.lojavirtual.data.remote.CartsApiService

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideProductsApiService(retrofit: Retrofit): ProductsApiService {
        return retrofit.create(ProductsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCartsApiService(retrofit: Retrofit): CartsApiService {
        return retrofit.create(CartsApiService::class.java)
    }
}