package com.example.desarrolloresidencia.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desarrolloresidencia.Network.model.Login.LoginUsers
import com.example.desarrolloresidencia.Network.model.Login.User
import com.example.desarrolloresidencia.Repository.AmazonRepository
import com.example.desarrolloresidencia.utils.Auth.AuthListener
import com.example.desarrolloresidencia.utils.LEArchivos
import com.example.desarrolloresidencia.utils.responseUser
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import java.lang.Exception

class LoginViewModel() : ViewModel() {

    var contexto: Context? = null

    var email: String? = null
    var password: String? = null
    var firstname: String? = null
    var middlename: String? = null
    var lastname: String? = null
    var dp: String? = null
    var authListener: AuthListener? = null
    var response: Response<LoginUsers>? = null

    fun onLoginButtonClick() {
        viewModelScope.launch {
            try {
                authListener?.onStarted()
                response = AmazonRepository().userLogin(email!!, password!!)
                Log.d("respuesta del server", response!!.body().toString())

                if (response!!.isSuccessful) {
                    if (response!!.body()!!.message.toString() == "false") {
                        authListener?.onFailure("""{"message":"${response!!.body()!!.message}"}""")

                    } else {
                        responseUser.message = response!!.body()!!.message
                        responseUser.token = response!!.body()!!.token
                        responseUser.user = response!!.body()!!.user

                        Log.d("login token", "${responseUser.token}")

                        validarU(
                            response!!.body()!!.message,
                            response!!.body()!!.token,
                            response!!.body()!!.user
                        )
                    }
                } else {
                    Log.e(
                        "LoginViewModel/onLoginButtonClick/mensaje de error",
                        "${response!!.errorBody()?.string()}"
                    )
                    authListener?.onFailure("${response!!.errorBody()?.string()}")
                }
            } catch (e: java.net.SocketTimeoutException) {
                authListener?.onFailure("""{"message":"${response?.body()?.message}"}""")
            } catch (e: java.lang.NullPointerException) {
                authListener?.onFailure("""{"message":"${response?.body()?.message}"}""")

            } catch (e: com.google.gson.stream.MalformedJsonException) {
                """{"message":"${response?.body()?.message}"}"""
            }
        }
    }

    fun LoginFacebook() {
        Log.e("login ViewModel", "comenzó el login de facebook")
        viewModelScope.launch {
            try {
                authListener?.onStarted()
                dp = contexto?.let { LEArchivos.Cargar(it) }
                Log.e("LoginViewModel/LoginFacebook/dp", "$dp")

                val response = AmazonRepository().userRegistroFacebook(
                    """$firstname $middlename""",
                    "$lastname",
                    " ",
                    "$email",
                    "$password",
                    "$dp"
                )


                if (response.isSuccessful) {
                    Log.d("login token", "${responseUser.token}")

                    onLoginButtonClick()
                    Log.e(
                        "LoginViewmodel/LoginFacebook/Successful",
                        "ya llegamos al loginbuttonclick"
                    )
                } else {
                    Log.e("LoginViewmodel/LoginFacebook/error", response.errorBody().toString())
                    onLoginButtonClick()
                    //authListener?.onFailure(response.errorBody()!!.string())
                }
            } catch (e: java.net.SocketTimeoutException) {
                authListener?.onFailure("""{"message":"No se pudo conectar con el servidor"}""")
            }
        }
    }


    fun validarU(message: String, token: String, user: User) {
        if (user.typeOfUser == "Consumer") {
            authListener?.onSuccess(message, token, user)
        } else {
            authListener?.onFailure("""{"message":"Solo pueden ingresar los usuarios de tipo consumidor"}""")
        }
    }

}