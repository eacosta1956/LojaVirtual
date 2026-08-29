package com.eldon.lojavirtual.presentation.users

import com.eldon.lojavirtual.domain.model.User

data class UsersUiState(
    val isLoading: Boolean = true,
    val users: List<User> = emptyList(),
    val error: String? = null
)