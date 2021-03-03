package com.example.desarrolloresidencia.Repository

import android.util.Log
import com.example.borradoraplicacin.API.data.model.LoginUsers
import com.example.desarrolloresidencia.Network.MyApi
import retrofit2.Call
import retrofit2.Response

class UserRepository {

    suspend fun userLogin(email: String, password: String): Response<LoginUsers>{
        Log.d("Repository", "$email $password")
        return MyApi().Logearse(email, password,"authentication", "loginUser")
    }
}