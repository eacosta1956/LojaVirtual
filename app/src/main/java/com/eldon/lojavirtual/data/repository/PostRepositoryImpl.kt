package com.eldon.lojavirtual.data.repository

import com.eldon.lojavirtual.data.remote.PostsApiService
import com.eldon.lojavirtual.domain.model.Post
import com.eldon.lojavirtual.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postsApiService: PostsApiService
) : PostRepository {

    override suspend fun getPosts(): List<Post> {
        return postsApiService.getPosts().posts.map { postDto ->
            postDto.toDomain()
        }
    }

    override suspend fun getPostById(postId: Int): Post {
        return postsApiService.getPostById(postId).toDomain()
    }
}