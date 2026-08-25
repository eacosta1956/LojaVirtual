package com.eldon.lojavirtual.presentation.products

import com.eldon.lojavirtual.domain.model.Product

data class ProductsUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)