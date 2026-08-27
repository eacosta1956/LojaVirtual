package com.eldon.lojavirtual.data.remote.dto

import com.eldon.lojavirtual.domain.model.Cart

data class CartDto(
    val id: Int,
    val products: List<CartProductDto>,
    val total: Double,
    val discountedTotal: Double,
    val userId: Int,
    val totalProducts: Int,
    val totalQuantity: Int
) {
    fun toDomain(): Cart {
        return Cart(
            id = id,
            userId = userId,
            products = products.map { it.toDomain() },
            total = total,
            discountedTotal = discountedTotal,
            totalProducts = totalProducts,
            totalQuantity = totalQuantity
        )
    }
}