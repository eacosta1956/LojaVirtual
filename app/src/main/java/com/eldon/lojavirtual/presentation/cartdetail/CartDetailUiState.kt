package com.eldon.lojavirtual.presentation.cartdetail

import com.eldon.lojavirtual.domain.model.Cart

data class CartDetailUiState(
    val isLoading: Boolean = true,
    val cart: Cart? = null,
    val error: String? = null
)