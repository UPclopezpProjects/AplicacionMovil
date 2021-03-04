package com.example.desarrolloresidencia.Network

import android.os.Message
import com.example.borradoraplicacin.API.data.model.LoginUsers
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface MyApi {
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
    @POST("register")
    suspend  fun Registrarse(
            @Field("email") email: String?,
            @Field("password") password: String?,
            @Field("nameOfUser") nameOfUser: String?,
            @Field("surnameP") surnameP: String?,
            @Field("surnameM") surnameM: String?,
            @Field("typeOfUser") typeOfUser: String?


    ): Response<com.example.desarrolloresidencia.Network.model.Message>


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

