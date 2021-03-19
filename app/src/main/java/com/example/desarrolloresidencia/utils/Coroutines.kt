package com.example.desarrolloresidencia.utils

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {
    var falla:String ?= null

    fun main (work:suspend (() -> Unit))=
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    work()
                } catch (e: java.io.IOException){
                    Log.e("Error de coroutine", "No se ha podido conectar con el servidor")
                    falla = "No se ha podido conectar con el servidor"
                }

            }

    fun IO (work: suspend (() -> Unit)) =
            CoroutineScope(Dispatchers.IO).launch {
                work()
            }

}