package com.example.desarrolloresidencia.Repository

<<<<<<< Updated upstream
import android.util.Log
import com.example.desarrolloresidencia.Network.Apis.ApiTrazabilidad
import com.example.desarrolloresidencia.Network.Apis.MyApi
=======
import com.example.desarrolloresidencia.Network.Apis.APIAmazon
>>>>>>> Stashed changes
import com.example.desarrolloresidencia.Network.model.Trazabilidad.Trazabilidad
import retrofit2.Response

class TrazabilidadRepository {
<<<<<<< Updated upstream
    suspend fun trazabilidadConsulta(QR: String): Response<Trazabilidad> {
        return ApiTrazabilidad().Trazabilidad(QR)
=======
    suspend fun trazabilidadConsulta(QR: String, ID: String): Response<Trazabilidad> {
        //return ApiTrazabilidad().Trazabilidad("$QR", "$ID")
        return APIAmazon().Trazabilidad("$QR", "$ID")
>>>>>>> Stashed changes
    }
}