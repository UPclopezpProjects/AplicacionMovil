package com.example.desarrolloresidencia.Repository

import android.util.Log
import com.example.desarrolloresidencia.Network.Apis.ApiTrazabilidad
import com.example.desarrolloresidencia.Network.Apis.MyApi
import com.example.desarrolloresidencia.Network.model.Trazabilidad.Trazabilidad
import retrofit2.Response

class TrazabilidadRepository {
    suspend fun trazabilidadConsulta(QR: String): Response<Trazabilidad> {
        return ApiTrazabilidad().Trazabilidad(QR)
    }
}