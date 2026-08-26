package com.eldon.lojavirtual.presentation.productdetail

import com.eldon.lojavirtual.domain.model.Product

data class ProductDetailUiState(
    val isLoading: Boolean = true,
    val product: Product? = null,
    val error: String? = null
)
