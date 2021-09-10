package com.example.desarrolloresidencia.utils.Auth

interface AuthQr {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}