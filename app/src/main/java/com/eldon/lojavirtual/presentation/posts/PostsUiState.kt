package com.eldon.lojavirtual.presentation.posts

import com.eldon.lojavirtual.domain.model.Post

data class PostsUiState(
    val isLoading: Boolean = true,
    val posts: List<Post> = emptyList(),
    val error: String? = null
)