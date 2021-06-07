package com.example.desarrolloresidencia.Network.Apis

import com.example.desarrolloresidencia.Network.model.CreationC.CreacionConsumidor
import com.example.desarrolloresidencia.Network.model.Login.LoginUsers
<<<<<<< Updated upstream:app/src/main/java/com/example/desarrolloresidencia/Network/Apis/MyApi.kt
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {
=======
import com.example.desarrolloresidencia.Network.model.Trazabilidad.Trazabilidad
import com.example.desarrolloresidencia.utils.responseUser
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface APIAmazon {
<<<<<<< Updated upstream

    val correo: String?
    get() = responseUser.user?.email

>>>>>>> Stashed changes:app/src/main/java/com/example/desarrolloresidencia/Network/Apis/APIAmazon.kt
    //el "Logearse" sirve como nombre del método
    //el "FormUrlEncoded" indica que la petición tendrá un tipo MIME
=======
>>>>>>> Stashed changes
    @FormUrlEncoded
    @POST("login")
    suspend  fun Logearse(
            @Field("email") email: String?,
            @Field("password") password: String?,
            @Field("typeOfOperation") typeOfOperation: String?,
            @Field("nameOfOperation") nameOfOperation: String?
    ): Response<LoginUsers>

    @FormUrlEncoded
<<<<<<< Updated upstream
    @POST("register")
=======
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
>>>>>>> Stashed changes
    suspend  fun Registrarse(
            @Field("email") email: String?,
            @Field("password") password: String?,
            @Field("nameOfUser") nameOfUser: String?,
            @Field("surnameP") surnameP: String?,
            @Field("surnameM") surnameM: String?,
            @Field("typeOfUser") typeOfUser: String?
    ): Response<CreacionConsumidor>

<<<<<<< Updated upstream:app/src/main/java/com/example/desarrolloresidencia/Network/Apis/MyApi.kt
=======
    @FormUrlEncoded
    @POST("traceability")
    suspend  fun Trazabilidad(
            @Field("QR") QR: String?,
            @Field("ID") ID: String?
    ): Response<Trazabilidad>
>>>>>>> Stashed changes:app/src/main/java/com/example/desarrolloresidencia/Network/Apis/APIAmazon.kt

    companion object{
        operator fun invoke() : APIAmazon {
            return Retrofit.Builder()
                    .baseUrl("http://52.202.214.13:80")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(APIAmazon::class.java)
        }
    }
}