package com.eldon.lojavirtual.data.repository

import com.eldon.lojavirtual.data.remote.ProductsApiService
import com.eldon.lojavirtual.domain.model.Product
import com.eldon.lojavirtual.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ProductsApiService
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return apiService.getProducts().products.map { productDto ->
            productDto.toDomain()
        }
    }

    override suspend fun getProductById(productId: Int): Product {
        return apiService.getProductById(productId).toDomain()
    }
}