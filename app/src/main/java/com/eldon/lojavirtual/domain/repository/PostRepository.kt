package com.eldon.lojavirtual.domain.repository

import com.eldon.lojavirtual.domain.model.Post

interface PostRepository {

    suspend fun getPosts(): List<Post>

    suspend fun getPostById(postId: Int): Post
}