package com.example.desarrolloresidencia.utils.Auth

import com.example.desarrolloresidencia.Network.model.Trazabilidad.Message

interface AuthQr {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}