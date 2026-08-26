package com.eldon.lojavirtual.domain.repository

import com.eldon.lojavirtual.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>

    suspend fun getProductById(productId: Int): Product
}