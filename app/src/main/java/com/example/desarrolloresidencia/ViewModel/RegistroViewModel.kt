package com.example.desarrolloresidencia.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desarrolloresidencia.Network.model.CreationC.CreacionConsumidor
import com.example.desarrolloresidencia.Repository.AmazonRepository
import com.example.desarrolloresidencia.utils.Auth.AuthRegistro
import com.example.desarrolloresidencia.utils.LEArchivos
import kotlinx.coroutines.launch
import retrofit2.Response

class RegistroViewModel(): ViewModel() {
    var contexto: Context? = null

    var nombre: String? = null
    var apellidoP: String? = null
    var apellidoM: String? = null
    var email: String? = null
    var password: String? = null
    var dp: String? = null
    var authListener: AuthRegistro? = null
    var response: Response<CreacionConsumidor>? =null

    fun onLoginButtonClick() {
        viewModelScope.launch {
            authListener?.onStarted()
            try {
                dp = contexto?.let { LEArchivos.Cargar(it) }
                //CoroutinesRU.main {
                response = AmazonRepository().userRegistro(nombre!!, apellidoP!!, apellidoM!!, email!!, password!!, dp.toString()!!)
                Log.d("RegistroViewModel/onLoginButtonClick/response", "${response!!.body().toString()}")
                if (response!!.isSuccessful) {
                    Log.d("RegistroViewModel/response", response!!.body().toString())

                    authListener?.onSuccess(response!!.body()!!.message)
                    Log.d("si jaló", "${response!!.body()!!.message}")
                } else {
                    authListener?.onFailure(response!!.errorBody()!!.string())
                }
            } catch (e: java.net.SocketTimeoutException) {
                authListener?.onFailure("""{"message":"No se pudo conectar con el servidor"}""")
            }
            catch (e: java.lang.NullPointerException){
                authListener?.onFailure(response!!.errorBody().toString())
                Log.e("RegistroViewModel/onLoginButtonClick/catch2", response!!.errorBody().toString())
            }
        }
    }

}