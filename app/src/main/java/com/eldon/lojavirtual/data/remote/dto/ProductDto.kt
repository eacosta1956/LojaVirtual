package com.eldon.lojavirtual.data.remote.dto

import com.eldon.lojavirtual.domain.model.Product

data class ProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val rating: Double,
    val thumbnail: String,
    val images: List<String>
) {
    fun toDomain(): Product {
        return Product(
            id = id,
            title = title,
            description = description,
            category = category,
            price = price,
            rating = rating,
            thumbnailUrl = thumbnail,
            images = images
        )
    }
}