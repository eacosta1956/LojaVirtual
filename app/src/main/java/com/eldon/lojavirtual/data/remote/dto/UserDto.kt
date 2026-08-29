package com.eldon.lojavirtual.data.remote.dto

import com.eldon.lojavirtual.domain.model.User

data class UserDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val email: String,
    val phone: String,
    val image: String,
    val address: AddressDto,
    val university: String,
    val company: CompanyDto
) {
    fun toDomain(): User {
        return User(
            id = id,
            firstName = firstName,
            lastName = lastName,
            age = age,
            gender = gender,
            email = email,
            phone = phone,
            imageUrl = image,
            address = address.address,
            city = address.city,
            state = address.state,
            country = address.country,
            university = university,
            companyName = company.name,
            companyTitle = company.title
        )
    }
}