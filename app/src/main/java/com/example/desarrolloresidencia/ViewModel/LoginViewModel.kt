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
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import com.facebook.login.LoginManager
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import org.json.JSONObject
import retrofit2.Response
import java.lang.Exception
import java.util.concurrent.*
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.coroutines.suspendCoroutine

class LoginViewModel() : ViewModel() {

    var contexto: Context? = null

    var email: String? = null
    var password: String? = null
    var firstname: String? = null
    var middlename: String? = null
    var lastname: String? = null
    var dp: String? = null
    var authListener: AuthListener? = null

    fun onLoginButtonClick() {
        viewModelScope.launch {
            try {
                authListener?.onStarted()
                Log.d("antes del response", "1")

                var response: Response<LoginUsers>
                withTimeout(5000L) {
                    response = AmazonRepository().userLogin(email!!, password!!)
                }


                Log.d("respuesta del server", "2")

                if (response!!.isSuccessful) {
                    if (response!!.body()!!.message.toString() == "false") {
                        authListener?.onFailure("""{"message":"${response!!.body()!!.message}"}""")

                    } else {
                        responseUser.message = response!!.body()!!.message
                        responseUser.token = response!!.body()!!.token
                        responseUser.user = response!!.body()!!.user

                        validarU(
                            response!!.body()!!.message,
                            response!!.body()!!.token,
                            response!!.body()!!.user
                        )
                    }
                } else {
                    //Log.e("LoginViewModel/onLoginButtonClick/mensaje de error", "${response!!.errorBody()?.string()}")
                    Log.e(
                        "LoginViewModel/onLoginButtonClick/mensaje de error",
                        response!!.code().toString()
                    )
                    authListener?.onFailure("${response!!.errorBody()?.string()}")
                    Log.e(
                        "LoginViewModel/onLoginButtonClick/mensaje de error",
                        "onLoginButtonClick"
                    )
                }
            } catch (e: java.net.SocketTimeoutException) {
                authListener?.onFailure("""{"message":"No se pudo conectar con el servidor"}""")
            } /*catch (e: java.lang.NullPointerException) {
                authListener?.onFailure("""{"message":"$e"}""")
            }*/ catch (e: com.google.gson.stream.MalformedJsonException) {
                authListener?.onFailure("""{"message":"Error al realizar la consulta, verifica la dirección IP del servidor"}""")
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
                    if (response.body()!!.message == "true") {
                        Log.e(
                            "LoginViewmodel/LoginFacebook/Successful",
                            "ya llegamos al loginbuttonclick"
                        )
                        onLoginButtonClick()
                    } else {
                        Log.d("LoginViewModel/loginfacebook/linea 117", "${response.body()!!.message}")
                        authListener?.onFailure("""{"message":"${response.body()!!.message}"}""")
                    }
                } else {
                    //Log.e("LoginViewmodel/LoginFacebook/error", response.errorBody().toString())
                    Log.e("LoginViewmodel/LoginFacebook/error", response.code().toString())
                    if (response.code() != 404) {
                        onLoginButtonClick()
                    } else {
                        authListener?.onFailure("""{"message":"El servidor no responde, verifica la dirección IP"}""")
                    }

                    Log.e("LoginViewModel/LoginFacebook/error", "LoginFacebook")
                }
            } catch (e: java.net.SocketTimeoutException) {
                authListener?.onFailure("""{"message":"No se pudo conectar con el servidor"}""")
            }
        }
    }

    fun validarU(message: String?, token: String?, user: User?) {
        if (token == null && user == null) {
            authListener?.onFailure("""{"message":"$message"}""")
        } else {
            if (user!!.typeOfUser == "Consumer") {
                authListener?.onSuccess(message!!, token!!, user!!)
            } else {
                authListener?.onFailure("""{"message":"Solo pueden ingresar los usuarios de tipo consumidor"}""")
            }
        }
    }

}