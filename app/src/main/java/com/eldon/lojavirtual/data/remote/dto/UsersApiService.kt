package com.eldon.lojavirtual.data.remote

import com.eldon.lojavirtual.data.remote.dto.UserDto
import com.eldon.lojavirtual.data.remote.dto.UsersResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApiService {

    @GET("users")
    suspend fun getUsers(): UsersResponseDto

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") userId: Int
    ): UserDto
}