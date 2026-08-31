package com.eldon.lojavirtual.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eldon.lojavirtual.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = PostsUiState(isLoading = true)

            try {
                val posts = getPostsUseCase()

                _uiState.value = PostsUiState(
                    isLoading = false,
                    posts = posts
                )
            } catch (exception: Exception) {
                _uiState.value = PostsUiState(
                    isLoading = false,
                    error = exception.message
                        ?: "Não foi possível carregar as postagens."
                )
            }
        }
    }
}