package com.eldon.lojavirtual.domain.repository

import com.eldon.lojavirtual.domain.model.User

interface UserRepository {

    suspend fun getUsers(): List<User>

    suspend fun getUserById(userId: Int): User
}