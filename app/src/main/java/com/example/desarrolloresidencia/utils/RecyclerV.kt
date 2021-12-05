package com.example.desarrolloresidencia.utils

interface RecyclerV {
    fun anterior(posicion: Int, ubication: String)
    fun siguiente(posicion: Int, ubication: String)
    fun descripcion(descripcion: String)
    fun transaccion(transaccion: String, contract: String, hash: String)
    fun GetLog(transaccion: String, contract: String)
}