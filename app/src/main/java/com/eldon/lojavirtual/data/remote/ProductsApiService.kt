package com.eldon.lojavirtual.data.remote

import com.eldon.lojavirtual.data.remote.dto.ProductDto
import com.eldon.lojavirtual.data.remote.dto.ProductsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsApiService {

    @GET("products")
    suspend fun getProducts(): ProductsResponseDto

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") productId: Int
    ): ProductDto
}