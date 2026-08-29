package com.eldon.lojavirtual.data.repository

import com.eldon.lojavirtual.data.remote.CartsApiService
import com.eldon.lojavirtual.domain.model.Cart
import com.eldon.lojavirtual.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartsApiService: CartsApiService
) : CartRepository {

    override suspend fun getCarts(): List<Cart> {
        return cartsApiService.getCarts().carts.map { cartDto ->
            cartDto.toDomain()
        }
    }

    override suspend fun getCartById(cartId: Int): Cart {
        return cartsApiService.getCartById(cartId).toDomain()
    }
}