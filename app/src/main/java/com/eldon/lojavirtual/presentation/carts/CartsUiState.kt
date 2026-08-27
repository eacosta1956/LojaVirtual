package com.eldon.lojavirtual.presentation.carts

import com.eldon.lojavirtual.domain.model.Cart

data class CartsUiState(
    val isLoading: Boolean = true,
    val carts: List<Cart> = emptyList(),
    val error: String? = null
)