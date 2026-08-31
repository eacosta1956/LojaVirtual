package com.eldon.lojavirtual.presentation.postdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eldon.lojavirtual.domain.usecase.GetPostByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val getPostByIdUseCase: GetPostByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun loadPost(postId: Int) {
        viewModelScope.launch {
            _uiState.value = PostDetailUiState(isLoading = true)

            try {
                val post = getPostByIdUseCase(postId)

                _uiState.value = PostDetailUiState(
                    isLoading = false,
                    post = post
                )
            } catch (exception: Exception) {
                _uiState.value = PostDetailUiState(
                    isLoading = false,
                    error = exception.message
                        ?: "Não foi possível carregar a postagem."
                )
            }
        }
    }
}