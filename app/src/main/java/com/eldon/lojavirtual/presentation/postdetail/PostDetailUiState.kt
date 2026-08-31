package com.eldon.lojavirtual.presentation.postdetail

import com.eldon.lojavirtual.domain.model.Post

data class PostDetailUiState(
    val isLoading: Boolean = true,
    val post: Post? = null,
    val error: String? = null
)