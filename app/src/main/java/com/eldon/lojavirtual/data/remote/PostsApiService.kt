package com.eldon.lojavirtual.data.remote

import com.eldon.lojavirtual.data.remote.dto.PostDto
import com.eldon.lojavirtual.data.remote.dto.PostsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApiService {

    @GET("posts")
    suspend fun getPosts(): PostsResponseDto

    @GET("posts/{id}")
    suspend fun getPostById(
        @Path("id") postId: Int
    ): PostDto
}