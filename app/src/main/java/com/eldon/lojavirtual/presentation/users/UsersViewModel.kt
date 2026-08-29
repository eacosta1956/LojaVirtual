package com.eldon.lojavirtual.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eldon.lojavirtual.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UsersUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = UsersUiState(isLoading = true)

            try {
                val users = getUsersUseCase()
                _uiState.value = UsersUiState(
                    isLoading = false,
                    users = users
                )
            } catch (exception: Exception) {
                _uiState.value = UsersUiState(
                    isLoading = false,
                    error = exception.message
                        ?: "Não foi possível carregar os usuários."
                )
            }
        }
    }
}