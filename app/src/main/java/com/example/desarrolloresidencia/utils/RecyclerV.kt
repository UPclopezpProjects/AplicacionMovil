package com.example.desarrolloresidencia.utils

import com.example.desarrolloresidencia.Network.model.Trazabilidad.mensaje

interface RecyclerV {
    fun anterior(posicion: Int, ubication: String)
    fun siguiente(posicion: Int, ubication: String)
    fun descripcion(descripcion: String)
    fun mensaje(mensaje: String)
}