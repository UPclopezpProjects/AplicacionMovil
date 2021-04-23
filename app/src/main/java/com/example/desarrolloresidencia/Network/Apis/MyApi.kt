package com.example.desarrolloresidencia.Network.Apis

import com.example.desarrolloresidencia.Network.model.CreationC.CreacionConsumidor
import com.example.desarrolloresidencia.Network.model.Login.LoginUsers
import com.example.desarrolloresidencia.utils.responseUser
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import retrofit2.http.Header as Header


interface MyApi {
    
    val correo: String?
        get() = responseUser.user?.email

    //el "Logearse" sirve como nombre del método
    //el "FormUrlEncoded" indica que la petición tendrá un tipo MIME
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
            @Field("email") email: String?,
            @Field("password") password: String?,
            @Field("nameOfUser") nameOfUser: String?,
            @Field("status") status: String?,
            @Field("surnameA") surnameA: String?,
            @Field("surnameB") surnameB: String?,
            @Field("typeOfOperation") typeOfOperation: String?,
            @Field("nameOfOperation") nameOfOperation: String?,
            @Field("hashX") hashX: String?,
            @Path("id") id:String,
            @Header("Authorization") Authorization:String
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
            @Field("hashX") hashX: String?
    ): Response<CreacionConsumidor>
    
    companion object{
        operator fun invoke() : MyApi {
            return Retrofit.Builder()
                    .baseUrl("http://10.0.0.5:3001/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MyApi::class.java)
        }
    }
}

