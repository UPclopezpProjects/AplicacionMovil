package com.example.desarrolloresidencia.utils

import com.example.desarrolloresidencia.Network.model.Login.LoginUsers
import com.example.desarrolloresidencia.Network.model.Login.User
import com.example.desarrolloresidencia.Network.model.Trazabilidad.Message

object responseUser {
    var message: Boolean ?= null
    var token : String ?= null
    var user: User ?= null
}