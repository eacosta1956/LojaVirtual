package com.eldon.lojavirtual.domain.usecase

import com.eldon.lojavirtual.domain.model.Post
import com.eldon.lojavirtual.domain.repository.PostRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postRepository: PostRepository
) {

    suspend operator fun invoke(): List<Post> {
        return postRepository.getPosts()
    }
}