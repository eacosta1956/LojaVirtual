package com.eldon.lojavirtual.presentation.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eldon.lojavirtual.domain.usecase.GetProductByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun loadProduct(productId: Int) {
        viewModelScope.launch {
            _uiState.value = ProductDetailUiState(isLoading = true)

            try {
                val product = getProductByIdUseCase(productId)
                _uiState.value = ProductDetailUiState(product = product)
            } catch (exception: Exception) {
                _uiState.value = ProductDetailUiState(
                    isLoading = false,
                    error = exception.message ?: "Não foi possível carregar o produto."
                )
            }
        }
    }
}