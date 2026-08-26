package com.eldon.lojavirtual.domain.model

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val rating: Double,
    val thumbnailUrl: String,
    val images: List<String>
)
