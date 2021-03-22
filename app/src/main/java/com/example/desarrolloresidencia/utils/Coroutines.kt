package com.example.desarrolloresidencia.utils

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {
    var contexto: Context ?= null
    fun main (work:suspend (() -> Unit))=
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    work()
                } catch (e: java.io.IOException){
                    Toast.makeText(contexto, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show()
                }


            }

    fun IO (work: suspend (() -> Unit)) =
            CoroutineScope(Dispatchers.IO).launch {
                work()
            }

}