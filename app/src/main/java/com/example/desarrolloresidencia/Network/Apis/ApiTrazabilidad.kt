package com.example.desarrolloresidencia.Network.Apis

import com.example.desarrolloresidencia.Network.model.Login.LoginUsers
import com.example.desarrolloresidencia.Network.model.Trazabilidad.Trazabilidad
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiTrazabilidad {
    @FormUrlEncoded
    @POST("traceability")
    suspend  fun Trazabilidad(
            @Field("QR") QR: String?
    ): Response<Trazabilidad>


    companion object{
        operator fun invoke() : ApiTrazabilidad {
            return Retrofit.Builder()
                    .baseUrl("http://10.0.0.5:3006/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiTrazabilidad::class.java)
        }
    }
}