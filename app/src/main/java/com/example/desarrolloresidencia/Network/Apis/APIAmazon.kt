package com.example.desarrolloresidencia.Network.Apis

import android.content.Context
import com.example.desarrolloresidencia.Network.model.CreationC.CreacionConsumidor
import com.example.desarrolloresidencia.Network.model.GetLog.getLog
import com.example.desarrolloresidencia.Network.model.Login.LoginUsers
import com.example.desarrolloresidencia.Network.model.Trazabilidad.Trazabilidad
import com.example.desarrolloresidencia.utils.LEArchivos
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


interface APIAmazon {
    @FormUrlEncoded
    @POST("login")
    suspend fun Logearse(
        @Field("email") email: String?,
        @Field("password") password: String?,
        @Field("typeOfOperation") typeOfOperation: String?,
        @Field("nameOfOperation") nameOfOperation: String?
    ): Response<LoginUsers>

    @FormUrlEncoded
    @PUT("userUpdate/{id}")
    suspend fun actualizarUsurio(
        @Field("password") password: String?,
        @Field("nameOfUser") nameOfUser: String?,
        @Field("status") status: String?,
        @Field("surnameA") surnameA: String?,
        @Field("surnameB") surnameB: String?,
        @Field("typeOfOperation") typeOfOperation: String?,
        @Field("nameOfOperation") nameOfOperation: String?,
        @Field("hashX") hashX: String?,
        @Path("id") id: String,
        @Header("Authorization") Authorization: String,
        @Field("email") email: String?
    ): Response<LoginUsers>

    //@GET("getLog?Atr={atr}&Asc={asc}&token={Token}")
    @GET("getLog")
    suspend fun getLog(
        @Query("Atr") atr: String?,
        @Query("Asc") asc: String?,
        @Query("token") Token: String
    ): Response<getLog>

    @FormUrlEncoded
    @POST("emailToReset")
    suspend fun recuperarPassword(
        @Field("email") email: String?
    ): Response<com.example.desarrolloresidencia.Network.model.RecuperarPass.Response>

    @FormUrlEncoded
    @POST("userCreation")
    suspend fun Registrarse(
        @Field("email") email: String?,
        @Field("password") password: String?,
        @Field("surnameA") surnameA: String?,
        @Field("surnameB") surnameB: String?,
        @Field("nameOfUser") nameOfUser: String?,
        @Field("typeOfUser") typeOfUser: String?,
        @Field("status") status: String?,
        @Field("dp") dp: String?,
        @Field("typeOfOperation") typeOfOperation: String?,
        @Field("nameOfOperation") nameOfOperation: String?,
        @Field("hashX") hashX: String?,
    ): Response<CreacionConsumidor>

    @FormUrlEncoded
    @POST("userCreation")
    suspend fun RegistrarseFacebook(
        @Field("email") email: String?,
        @Field("password") password: String?,
        @Field("surnameA") surnameA: String?,
        @Field("surnameB") surnameB: String?,
        @Field("nameOfUser") nameOfUser: String?,
        @Field("typeOfUser") typeOfUser: String?,
        @Field("status") status: String?,
        @Field("dp") dp: String?,
        @Field("typeOfOperation") typeOfOperation: String?,
        @Field("nameOfOperation") nameOfOperation: String?,
        @Field("hashX") hashX: String?,
        @Field("facebook") facebook: Boolean
    ): Response<CreacionConsumidor>

    @FormUrlEncoded
    @POST("traceability")
    suspend fun Trazabilidad(
        //@Field("Code") Code: String?,
        @Field("ID") ID: String?
    ): Response<Trazabilidad>

    companion object {
        var context: Context? = null

        operator fun invoke(): APIAmazon {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl("http://${LEArchivos.CargarIP(context!!)}/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIAmazon::class.java)
        }
    }
}