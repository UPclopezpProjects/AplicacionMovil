package com.example.desarrolloresidencia.utils

interface RecyclerV {
    fun anterior(posicion: Int, ubication: String)
    fun siguiente(posicion: Int, ubication: String)
    fun descripcion(descripcion: String, hash: String)
    fun transaccion(transaccion: String)
    fun contract(contract: String)
}