package com.example.desarrolloresidencia.Repository

import android.util.Log
import com.example.desarrolloresidencia.Network.model.Login.LoginUsers
import com.example.desarrolloresidencia.Network.Apis.MyApi
import com.example.desarrolloresidencia.Network.model.CreationC.CreacionConsumidor
import com.example.desarrolloresidencia.utils.LEArchivos
import com.example.desarrolloresidencia.utils.MD5
import org.json.JSONObject
import retrofit2.Response

class UserRepository {

    suspend fun userLogin(email: String, password: String): Response<LoginUsers> {
        Log.d("Repository", "$email $password")
        return MyApi().Logearse(email, password, "authentication", "loginUser")
    }

    suspend fun userRegistro(nombre:String, apellidoP:String, apellidoM:String, email:String, contrasena:String, dp:String): Response<CreacionConsumidor> {
        var jsonOsman = """{"email":"$email","password":"$contrasena","surnameA":"$apellidoP","surnameB":"$apellidoM","nameOfUser":"$nombre","typeOfUser":"Consumer","status":"false","creationDate":"xx-xx-xxxx","dp":$dp,"addressU":"0xf26F59ac3801F419Abe44CA7d56FfC8fB8142BbB","typeOfOperation":"create","nameOfOperation":"createConsumer"}"""

        Log.d("el string", "$jsonOsman")
        var objeto = JSONObject(jsonOsman)
        Log.d("objeto json", objeto.toString())
        var hash = MD5().md5(objeto.toString())
        Log.d("el hash", "$hash")

        return MyApi().Registrarse("$email", "$contrasena", "$apellidoP", "$apellidoM", "$nombre", "Consumer","false", "xx-xx-xxxx", "$dp", "0xf26F59ac3801F419Abe44CA7d56FfC8fB8142BbB", "create", "createConsumer", "$hash")
    }
}