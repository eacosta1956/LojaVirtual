package com.eldon.lojavirtual.presentation.carts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eldon.lojavirtual.domain.usecase.GetCartsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartsViewModel @Inject constructor(
    private val getCartsUseCase: GetCartsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadCarts()
    }

    fun loadCarts() {
        viewModelScope.launch {
            _uiState.value = CartsUiState(isLoading = true)

            try {
                val carts = getCartsUseCase()
                _uiState.value = CartsUiState(
                    isLoading = false,
                    carts = carts
                )

            } catch (exception: Exception) {
                _uiState.value = CartsUiState(
                    isLoading = false,
                    error = exception.message ?: "Não foi possível carregar os carrinhos."
                )
            }
        }
    }
}