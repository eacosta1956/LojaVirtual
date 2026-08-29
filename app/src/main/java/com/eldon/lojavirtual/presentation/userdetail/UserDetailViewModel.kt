package com.eldon.lojavirtual.presentation.userdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eldon.lojavirtual.domain.usecase.GetUserByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun loadUser(userId: Int) {
        viewModelScope.launch {
            _uiState.value = UserDetailUiState(isLoading = true)

            try {
                val user = getUserByIdUseCase(userId)
                _uiState.value = UserDetailUiState(
                    isLoading = false,
                    user = user
                )
            } catch (exception: Exception) {
                _uiState.value = UserDetailUiState(
                    isLoading = false,
                    error = exception.message
                        ?: "Não foi possível carregar o usuário."
                )
            }
        }
    }
}