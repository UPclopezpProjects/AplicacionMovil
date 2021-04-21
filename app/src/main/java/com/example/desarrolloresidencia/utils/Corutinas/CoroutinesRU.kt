package com.example.desarrolloresidencia.utils.Corutinas

import com.example.desarrolloresidencia.utils.Auth.AuthListener
import com.example.desarrolloresidencia.utils.Auth.AuthRegistro
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object CoroutinesRU {
    var authListener: AuthRegistro?= null
    fun main (work:suspend (() -> Unit))=
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    work()
                } catch (e: java.io.IOException){
                    //Toast.makeText(contexto, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show()
                    authListener?.onFailure("Error al conectar con el servidor")
                }


            }

}