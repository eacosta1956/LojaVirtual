package com.eldon.lojavirtual.domain.usecase

import com.eldon.lojavirtual.domain.model.Cart
import com.eldon.lojavirtual.domain.repository.CartRepository
import javax.inject.Inject

class GetCartByIdUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(cartId: Int): Cart {
        return cartRepository.getCartById(cartId)
    }
}