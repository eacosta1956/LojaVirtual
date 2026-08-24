package com.eldon.lojavirtual.domain.usecase

import com.eldon.lojavirtual.domain.model.Product
import com.eldon.lojavirtual.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): List<Product> {
        return productRepository.getProducts()
    }
}