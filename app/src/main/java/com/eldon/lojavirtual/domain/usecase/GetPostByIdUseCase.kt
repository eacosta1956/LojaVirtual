package com.eldon.lojavirtual.domain.usecase

import com.eldon.lojavirtual.domain.model.Post
import com.eldon.lojavirtual.domain.repository.PostRepository
import javax.inject.Inject

class GetPostByIdUseCase @Inject constructor(
    private val postRepository: PostRepository
) {

    suspend operator fun invoke(postId: Int): Post {
        return postRepository.getPostById(postId)
    }
}