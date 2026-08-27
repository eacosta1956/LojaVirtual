package com.eldon.lojavirtual.domain.repository

import com.eldon.lojavirtual.domain.model.Cart

interface CartRepository {

    suspend fun getCarts(): List<Cart>

    suspend fun getCartById(cartId: Int): Cart
}