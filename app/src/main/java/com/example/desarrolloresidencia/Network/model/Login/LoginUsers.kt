package com.example.desarrolloresidencia.Network.model.Login

data class LoginUsers(
    val message: Boolean,
    val token: String,
    val user: User
)