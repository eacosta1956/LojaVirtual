package com.eldon.lojavirtual.domain.usecase

import com.eldon.lojavirtual.domain.model.User
import com.eldon.lojavirtual.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): List<User> {
        return userRepository.getUsers()
    }
}