package com.example.desarrolloresidencia.Repository

import com.example.desarrolloresidencia.Network.Apis.APIAmazon
import com.example.desarrolloresidencia.Network.model.Trazabilidad.Trazabilidad
import retrofit2.Response

class TrazabilidadRepository {
    suspend fun trazabilidadConsulta(QR: String, ID: String): Response<Trazabilidad> {
        //return ApiTrazabilidad().Trazabilidad("$QR", "$ID")
        return APIAmazon().Trazabilidad("$QR", "$ID")
    }
}