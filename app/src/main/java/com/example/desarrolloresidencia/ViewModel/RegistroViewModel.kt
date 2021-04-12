package com.example.desarrolloresidencia.ViewModel

import android.content.Context
import android.util.JsonWriter
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.desarrolloresidencia.Network.model.CreationC.CreacionConsumidor
import com.example.desarrolloresidencia.Repository.UserRepository
import com.example.desarrolloresidencia.utils.Auth.AuthRegistro
import com.example.desarrolloresidencia.utils.Corutinas.Coroutines
import com.example.desarrolloresidencia.utils.Corutinas.CoroutinesRU
import com.example.desarrolloresidencia.utils.LEArchivos
import java.io.OutputStreamWriter
import java.io.StringWriter
import java.lang.System.out

class RegistroViewModel(): ViewModel() {
    var contexto: Context ?= null

    var nombre: String ?= null
    var apellidoP: String ?= null
    var apellidoM: String ?= null
    var email: String ?= null
    var password: String ?= null
    var dp:String ?= null
    var authListener: AuthRegistro ?= null


    fun onLoginButtonClick(){
        //var json = """{ "createAdministrator": false, "createTUser": false, "updateMe": true, "updateAdministrator": false, "updateTUser": false, "deleteMe": true, "deleteAdministrator": false, "deleteTUser": false, "readMe": true, "readAdministrator": false, "readTUser": false, "loginUser": true }"""

        //contexto?.let { LEArchivos.sobrescribir("$json", it) }
       authListener?.onStarted()
        //dp=""
       dp = contexto?.let { LEArchivos.Cargar(it) }

       CoroutinesRU.main {
            val response = UserRepository().userRegistro(nombre!!, apellidoP!!, apellidoM!!, email!!, password!!, dp.toString()!!)
            if (response.isSuccessful){
                authListener?.onSuccess(response.body()!!.message, response.body()!!.token, response.body()!!.user)
                Log.d("si jaló", "${response.body()!!.message}")
            } else{
                //authListener?.onFailure("${response.code()}")
                    //Log.e("error de registro", "${response.errorBody()!!.string()}")
                authListener?.onFailure(response.errorBody()!!.string())
                //validacionErr(response.code().toString())
            }
       }

    }

    fun validarStatus (status : String):String{
        if (status=="false"){
            return "Verifica tu correo electrónico"
        }else{
            return "Tu correo electrónico está verificado"
        }
    }
}