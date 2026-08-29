package com.eldon.lojavirtual.domain.model

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val email: String,
    val phone: String,
    val imageUrl: String,
    val address: String,
    val city: String,
    val state: String,
    val country: String,
    val university: String,
    val companyName: String,
    val companyTitle: String
)