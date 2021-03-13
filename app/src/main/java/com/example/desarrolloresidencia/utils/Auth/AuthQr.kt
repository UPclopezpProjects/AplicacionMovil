package com.example.desarrolloresidencia.utils.Auth

import com.example.desarrolloresidencia.Network.model.Login.User
import com.example.desarrolloresidencia.Network.model.Trazabilidad.Message

interface AuthQr {
    fun onStarted()
    fun onSuccess(message: Message)
    fun onFailure(message: String)
}