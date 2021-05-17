package com.example.desarrolloresidencia.utils.Corutinas

import android.util.Log
import com.example.desarrolloresidencia.utils.Auth.AuthQr
import com.example.desarrolloresidencia.utils.Auth.AuthRegistro
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object CoroutinesTraz {

    var authListener: AuthQr?= null

    fun main (work:suspend (() -> Unit)) = CoroutineScope(Dispatchers.Main).launch {
            try {
                work()
            } catch (e: java.io.IOException){
                //Toast.makeText(contexto, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show()
                authListener?.onFailure("Error al conectar con el servidor")
                Log.e("CoroutinesTraza", e.toString())
            }


        }
}