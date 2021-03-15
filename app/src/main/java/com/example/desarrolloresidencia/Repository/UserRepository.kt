package com.example.desarrolloresidencia.Repository

import android.util.Log
import com.example.desarrolloresidencia.Network.model.Login.LoginUsers
import com.example.desarrolloresidencia.Network.Apis.MyApi
import com.example.desarrolloresidencia.Network.model.CreationC.CreacionConsumidor
import com.example.desarrolloresidencia.UI.Login
import okhttp3.ResponseBody
import retrofit2.Response
import java.lang.Exception

class UserRepository {

    suspend fun userLogin(email: String, password: String): Response<LoginUsers> {
            Log.d("Repository", "$email $password")
            return MyApi().Logearse(email, password, "authentication", "loginUser")
    }

    suspend fun userRegistro(nombre:String, apellidoP:String, apellidoM:String, email:String, contrasena:String): Response<CreacionConsumidor> {
        Log.d("Repository", "$email $contrasena")
        return MyApi().Registrarse("$email", "$contrasena", "$nombre", "$apellidoP", "$apellidoM", "Consumer")
        Log.d("después de la consulta repository", "hasta aquí todo bien")
    }
}