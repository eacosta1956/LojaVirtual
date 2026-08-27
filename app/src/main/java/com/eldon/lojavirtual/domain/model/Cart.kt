package com.eldon.lojavirtual.domain.model

data class Cart(
    val id: Int,
    val userId: Int,
    val products: List<CartProduct>,
    val total: Double,
    val discountedTotal: Double,
    val totalProducts: Int,
    val totalQuantity: Int
)