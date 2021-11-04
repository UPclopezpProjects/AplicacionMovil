package com.example.desarrolloresidencia.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desarrolloresidencia.Network.model.Login.User
import com.example.desarrolloresidencia.Repository.AmazonRepository
import com.example.desarrolloresidencia.utils.Auth.AuthListener
import com.example.desarrolloresidencia.utils.LEArchivos
import com.example.desarrolloresidencia.utils.responseUser
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.Exception

class LoginViewModel() : ViewModel()  {

    var contexto: Context? = null

    var email: String ?= null
    var password: String ?= null
    var firstname: String ?= null
    var middlename: String ?= null
    var lastname: String ?= null
    var dp: String? = null
    var authListener: AuthListener?= null

    fun onLoginButtonClick() {
        viewModelScope.launch {
            try {
                authListener?.onStarted()
                //Coroutines.main {
                //val response = UserRepository().userLogin(email!!, password!!)

                val response = AmazonRepository().userLogin(email!!, password!!)
            Log.d("respuesta del server", response.body().toString())


                if (response.isSuccessful) {
                    if(response.body()!!.message.toString() == "false"){
                        authListener?.onFailure("""{"message":"Error durante el login, ¿verificaste tu correo electrónico?"}""")

                    } else {
                        responseUser.message = response.body()!!.message
                        responseUser.token = response.body()!!.token
                        responseUser.user = response.body()!!.user

                        Log.d("login token", "${responseUser.token}")

                        validarU(response.body()!!.message, response.body()!!.token, response.body()!!.user)
                        //authListener?.onSuccess(response.body()!!.message, response.body()!!.token, response.body()!!.user)
                    }
                } else {
                    Log.e("LoginViewModel/onLoginButtonClick/mensaje de error", "${response.errorBody()?.string()}")
                    authListener?.onFailure("${response.errorBody()?.string()}")
                }
            }
            catch (e : java.net.SocketTimeoutException){
                authListener?.onFailure("""{"message":"No se pudo conectar con el servidor"}""")
            }
            catch (e : java.lang.NullPointerException){
                authListener?.onFailure("""{"message":"No se ha encontrado el correo electrónico, verifica tu correo o registrate"}""")

            }
            catch (e:com.google.gson.stream.MalformedJsonException){
                """{"message":"Error en la respuesta del servidor"}"""
            }
        }
    }

    fun LoginFacebook(){
        Log.e("login ViewModel", "comenzó el login de facebook")
        viewModelScope.launch {
            try {
                authListener?.onStarted()
                dp = contexto?.let { LEArchivos.Cargar(it) }

                val response = AmazonRepository().userRegistro("""$firstname $middlename""", "$lastname", " ", "$email", "$password", "$dp")


                if (response.isSuccessful) {
                    Log.d("login token", "${responseUser.token}")

                    onLoginButtonClick()
                    Log.e("LoginViewmodel", "ya llegamos al loginbuttonclick")
                } else {
                    Log.e("login viewmodel error", response.errorBody().toString())
                    authListener?.onFailure(response.errorBody()!!.string())
                }
            }
            catch (e : java.net.SocketTimeoutException){
                authListener?.onFailure("""{"message":"No se pudo conectar con el servidor"}""")
            }
        }
    }


    fun validarU(message: Boolean, token: String, user: User){
        if(user.typeOfUser=="Consumer"){
            authListener?.onSuccess(message, token, user)
        }else{
            authListener?.onFailure("""{"message":"Solo pueden ingresar los usuarios de tipo consumidor"}""")
        }
    }

}