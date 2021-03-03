package com.example.borradoraplicacin.API.data.model

data class LoginUsers(
    val message: Boolean,
    val token: String,
    val user: User
)