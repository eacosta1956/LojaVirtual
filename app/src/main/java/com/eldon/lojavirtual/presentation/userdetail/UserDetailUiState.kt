package com.eldon.lojavirtual.presentation.userdetail

import com.eldon.lojavirtual.domain.model.User

data class UserDetailUiState(
    val isLoading: Boolean = true,
    val user: User? = null,
    val error: String? = null
)