package com.example.desarrolloresidencia.Repository

import android.util.Log
import com.example.desarrolloresidencia.Network.Apis.APIAmazon
import com.example.desarrolloresidencia.Network.model.Login.LoginUsers
import com.example.desarrolloresidencia.Network.model.CreationC.CreacionConsumidor
<<<<<<< Updated upstream
import com.example.desarrolloresidencia.UI.Login
import okhttp3.ResponseBody
=======
import com.example.desarrolloresidencia.utils.MD5
import org.json.JSONObject
>>>>>>> Stashed changes
import retrofit2.Response
import java.lang.Exception

class UserRepository {

    suspend fun userLogin(email: String, password: String): Response<LoginUsers> {
<<<<<<< Updated upstream
            Log.d("Repository", "$email $password")
            return MyApi().Logearse(email, password, "authentication", "loginUser")
    }

    suspend fun userRegistro(nombre:String, apellidoP:String, apellidoM:String, email:String, contrasena:String): Response<CreacionConsumidor> {
        Log.d("Repository", "$email $contrasena")
        return MyApi().Registrarse("$email", "$contrasena", "$nombre", "$apellidoP", "$apellidoM", "Consumer")
        Log.d("después de la consulta repository", "hasta aquí todo bien")
=======
        Log.d("Repository", "$email $password")
        //return MyApi().Logearse(email, password, "authentication", "loginUser")
        return APIAmazon().Logearse(email, password, "authentication", "loginUser")
    }

    suspend fun actualizarUsuario(nombre:String, apellidoP:String, apellidoM:String, email:String, contrasena:String, id:String, token:String):Response<LoginUsers>{
        Log.d("repositorio id", "$id")
        Log.d("repositorio token", "$token")
        //return MyApi().actualizarUsurio("$email", "$contrasena", "$nombre", "true", "$apellidoP", "$apellidoM", "update", "updateMe", "48d6adacee72fea2034b636a029d3ea9", "$id", "$token")
<<<<<<< Updated upstream:app/src/main/java/com/example/desarrolloresidencia/Repository/UserRepository.kt
        return APIAmazon().actualizarUsurio("$email", "$contrasena", "$nombre", "true", "$apellidoP", "$apellidoM", "update", "updateMe", "48d6adacee72fea2034b636a029d3ea9", "$id", "$token")
=======
        return APIAmazon().actualizarUsurio("$contrasena", "$nombre", "true", "$apellidoP", "$apellidoM", "update", "updateMe", "48d6adacee72fea2034b636a029d3ea9", "$id", "$token", "$id")
>>>>>>> Stashed changes:app/src/main/java/com/example/desarrolloresidencia/Repository/AmazonRepository.kt
    }

    suspend fun recuperarPassword(email: String):Response<com.example.desarrolloresidencia.Network.model.RecuperarPass.Response>{
        //return MyApi().recuperarPassword("$email")
        return APIAmazon().recuperarPassword("$email")
    }

    suspend fun userRegistro(nombre:String, apellidoP:String, apellidoM:String, email:String, contrasena:String, dp:String): Response<CreacionConsumidor> {
<<<<<<< Updated upstream:app/src/main/java/com/example/desarrolloresidencia/Repository/UserRepository.kt
        var jsonOsman = """{"email":"$email","password":"$contrasena","surnameA":"$apellidoP","surnameB":"$apellidoM","nameOfUser":"$nombre","typeOfUser":"Consumer","status":"false","creationDate":"xx-xx-xxxx","dp":$dp,"addressU":"0xf26F59ac3801F419Abe44CA7d56FfC8fB8142BbB","typeOfOperation":"create","nameOfOperation":"createConsumer"}"""
=======
        Log.e("Repository nombre", "$nombre")
        Log.e("Repository apellidoP", "$apellidoP")
        Log.e("Repository apellidoM", "$apellidoM")
        Log.e("Repository email", "$email")
        Log.e("Repository contrasena", "$contrasena")
        Log.e("Repository dp", "$dp")

        var jsonOsman = """{ "email":"${email.toLowerCase()}","password":"$contrasena","surnameA":"$apellidoP","surnameB":"$apellidoM","nameOfUser":"$nombre","typeOfUser":"Consumer","status":"false","creationDate":"xx-xx-xxxx","dp":$dp,"addressU":"0xf26F59ac3801F419Abe44CA7d56FfC8fB8142BbB","typeOfOperation":"create","nameOfOperation":"createConsumer" }"""
>>>>>>> Stashed changes:app/src/main/java/com/example/desarrolloresidencia/Repository/AmazonRepository.kt

        Log.d("el string", "$jsonOsman")
        var objeto = JSONObject(jsonOsman)
        Log.d("objeto json", objeto.toString())
        var hash = MD5().md5(objeto.toString())
        Log.d("el hash", "$hash")

        //return MyApi().Registrarse("$email", "$contrasena", "$apellidoP", "$apellidoM", "$nombre", "Consumer","false", "xx-xx-xxxx", "$dp", "0xf26F59ac3801F419Abe44CA7d56FfC8fB8142BbB", "create", "createConsumer", "$hash")
        return APIAmazon().Registrarse("$email", "$contrasena", "$apellidoP", "$apellidoM", "$nombre", "Consumer","false", "xx-xx-xxxx", "$dp", "0xf26F59ac3801F419Abe44CA7d56FfC8fB8142BbB", "create", "createConsumer", "$hash")
>>>>>>> Stashed changes
    }
}