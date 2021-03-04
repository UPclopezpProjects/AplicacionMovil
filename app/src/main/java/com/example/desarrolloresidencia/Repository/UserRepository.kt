package com.example.desarrolloresidencia.Repository

import android.util.Log
import com.example.borradoraplicacin.API.data.model.LoginUsers
import com.example.desarrolloresidencia.Network.MyApi
import com.example.desarrolloresidencia.Network.model.Message
import retrofit2.Call
import retrofit2.Response

class UserRepository {

    suspend fun userLogin(email: String, password: String): Response<LoginUsers>{
        Log.d("Repository", "$email $password")
        return MyApi().Logearse(email, password,"authentication", "loginUser")
    }

    suspend fun userRegistro(nombre: String, apellidoP:String, apellidoM: String, email: String, contrasena: String): Response<Message> {
        Log.d("Repository", "$email $contrasena")
        return MyApi().Registrarse("$email", "$contrasena", "$nombre", "$apellidoP", "$apellidoM", "Consumer")
    }
}