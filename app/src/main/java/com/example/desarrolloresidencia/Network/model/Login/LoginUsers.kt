package com.example.desarrolloresidencia.Network.model.Login

data class LoginUsers(
    val message: Boolean,
    var token: String,
    val user: User
)