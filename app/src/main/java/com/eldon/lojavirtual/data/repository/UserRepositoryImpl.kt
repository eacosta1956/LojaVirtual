package com.eldon.lojavirtual.data.repository

import com.eldon.lojavirtual.data.remote.UsersApiService
import com.eldon.lojavirtual.domain.model.User
import com.eldon.lojavirtual.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val usersApiService: UsersApiService
) : UserRepository {

    override suspend fun getUsers(): List<User> {
        return usersApiService.getUsers().users.map { userDto ->
            userDto.toDomain()
        }
    }

    override suspend fun getUserById(userId: Int): User {
        return usersApiService.getUserById(userId).toDomain()
    }
}