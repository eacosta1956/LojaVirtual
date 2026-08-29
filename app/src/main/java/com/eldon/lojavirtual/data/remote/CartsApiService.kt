package com.eldon.lojavirtual.data.remote

import com.eldon.lojavirtual.data.remote.dto.CartDto
import com.eldon.lojavirtual.data.remote.dto.CartsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CartsApiService {

    @GET("carts")
    suspend fun getCarts(): CartsResponseDto

    @GET("carts/{id}")
    suspend fun getCartById(
        @Path("id") cartId: Int
    ): CartDto
}