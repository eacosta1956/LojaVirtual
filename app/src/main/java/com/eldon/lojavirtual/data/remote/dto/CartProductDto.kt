package com.eldon.lojavirtual.data.remote.dto

import com.eldon.lojavirtual.domain.model.CartProduct

data class CartProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val total: Double,
    val discountPercentage: Double,
    val discountedTotal: Double,
    val thumbnail: String
) {
    fun toDomain(): CartProduct {
        return CartProduct(
            id = id,
            title = title,
            price = price,
            quantity = quantity,
            total = total,
            discountPercentage = discountPercentage,
            discountedTotal = discountedTotal,
            thumbnailUrl = thumbnail
        )
    }
}