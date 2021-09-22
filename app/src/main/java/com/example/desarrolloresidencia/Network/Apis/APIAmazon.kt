package com.example.desarrolloresidencia.Network.Apis

import com.example.desarrolloresidencia.Network.model.CreationC.CreacionConsumidor
import com.example.desarrolloresidencia.Network.model.Login.LoginUsers
import com.example.desarrolloresidencia.Network.model.Trazabilidad.Trazabilidad
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface APIAmazon {
    @FormUrlEncoded
    @POST("login")
    suspend  fun Logearse(
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
            @Path("id") id:String,
            @Header("Authorization") Authorization:String,
            @Field("email") email: String?
    ): Response<LoginUsers>

    @FormUrlEncoded
    @POST("emailToReset")
    suspend fun recuperarPassword(
            @Field("email") email: String?
    ): Response<com.example.desarrolloresidencia.Network.model.RecuperarPass.Response>

    @FormUrlEncoded
    @POST("userCreation")
    suspend  fun Registrarse(
            @Field("email") email: String?,
            @Field("password") password: String?,
            @Field("surnameA") surnameA: String?,
            @Field("surnameB") surnameB: String?,
            @Field("nameOfUser") nameOfUser: String?,
            @Field("typeOfUser") typeOfUser: String?,
            @Field("status") status: String?,
            @Field("creationDate") creationDate: String?,
            @Field("dp") dp: String?,
            @Field("addressU") addressU: String?,
            @Field("typeOfOperation") typeOfOperation: String?,
            @Field("nameOfOperation") nameOfOperation: String?,
            @Field("hashX") hashX: String?,
            @Field("gas") gas: String?
    ): Response<CreacionConsumidor>

    @FormUrlEncoded
    @POST("traceability")
    suspend  fun Trazabilidad(
            //@Field("Code") Code: String?,
            @Field("ID") ID: String?
    ): Response<Trazabilidad>

    companion object{
        operator fun invoke() : APIAmazon {
            return Retrofit.Builder()
                    .baseUrl("http://52.202.214.13:80/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(APIAmazon::class.java)
        }
    }
}