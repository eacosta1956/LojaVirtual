package com.eldon.lojavirtual.presentation.cartdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eldon.lojavirtual.domain.usecase.GetCartByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartDetailViewModel @Inject constructor(
    private val getCartByIdUseCase: GetCartByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun loadCart(cartId: Int) {
        viewModelScope.launch {
            _uiState.value = CartDetailUiState(isLoading = true)

            try {
                val cart = getCartByIdUseCase(cartId)
                _uiState.value = CartDetailUiState(
                    isLoading = false,
                    cart = cart
                )
            } catch (exception: Exception) {
                _uiState.value = CartDetailUiState(
                    isLoading = false,
                    error = exception.message
                        ?: "Não foi possível carregar o carrinho."
                )
            }
        }
    }
}