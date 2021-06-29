package com.example.desarrolloresidencia.utils.Corutinas

import android.util.Log
import com.example.desarrolloresidencia.utils.Auth.AuthListener
import com.example.desarrolloresidencia.utils.Auth.AuthRecuperar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object CoroutinesRecCont {
    var authListener: AuthRecuperar?= null
    fun main (work:suspend (() -> Unit))=
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    work()
                } catch (e: java.io.IOException){
                    //Toast.makeText(contexto, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show()
                    authListener?.onFailure("Error al conectar con el servidor")
                    Log.e("Error de coroutine", e.toString())
                }


            }
}