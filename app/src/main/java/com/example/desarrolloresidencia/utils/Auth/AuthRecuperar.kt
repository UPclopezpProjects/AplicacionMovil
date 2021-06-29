package com.example.desarrolloresidencia.utils.Auth

interface AuthRecuperar {
    fun onStarted()
    fun onSuccess(message: String)
    fun onFailure(message: String)
}