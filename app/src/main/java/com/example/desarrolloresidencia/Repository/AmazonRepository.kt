package com.example.desarrolloresidencia.Repository

import android.util.Log
import com.example.desarrolloresidencia.Network.Apis.APIAmazon
import com.example.desarrolloresidencia.Network.model.CreationC.CreacionConsumidor
import com.example.desarrolloresidencia.Network.model.GetLog.Message
import com.example.desarrolloresidencia.Network.model.GetLog.getLog
import com.example.desarrolloresidencia.Network.model.Login.LoginUsers
import com.example.desarrolloresidencia.Network.model.Trazabilidad.Trazabilidad
import com.example.desarrolloresidencia.utils.MD5
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

class AmazonRepository {
    suspend fun trazabilidadConsulta(QR: String/*, ID: String*/): Response<Trazabilidad> {
        //return ApiTrazabilidad().Trazabilidad("$QR", "$ID")
        return APIAmazon().Trazabilidad("$QR"/*, "$ID"*/)
    }

    suspend fun userLogin(email: String, password: String): Response<LoginUsers> {
        Log.d("Repository", "$email $password")
        return APIAmazon().Logearse(email, password, "authentication", "loginUser")
    }

    suspend fun actualizarUsuario(nombre: String, apellidoP: String, apellidoM: String, contrasena: String, id: String, token: String): Response<LoginUsers> {
        Log.d("repositorio id", "$id")
        Log.d("repositorio token", "$token")
        //return MyApi().actualizarUsurio("$email", "$contrasena", "$nombre", "true", "$apellidoP", "$apellidoM", "update", "updateMe", "48d6adacee72fea2034b636a029d3ea9", "$id", "$token")
        return APIAmazon().actualizarUsurio(
            "$contrasena",
            "$nombre",
            "true",
            "$apellidoP",
            "$apellidoM",
            "update",
            "updateMe",
            "48d6adacee72fea2034b636a029d3ea9",
            "$id",
            "$token",
            "$id"
        )
    }

    suspend fun getLog(transaction: String, contract: String, token: String): Response<getLog>? {
        try {
            return APIAmazon().getLog(transaction, contract, token)
        } catch (e: com.google.gson.JsonSyntaxException) {
            return null
        }
    }

    suspend fun recuperarPassword(email: String): Response<com.example.desarrolloresidencia.Network.model.RecuperarPass.Response> {
        //return MyApi().recuperarPassword("$email")
        return APIAmazon().recuperarPassword("$email")
    }

    suspend fun userRegistro(nombre: String, apellidoP: String, apellidoM: String, email: String, contrasena: String, dp: String ): Response<CreacionConsumidor> {

        //var jsonOsman = """{ "email":"${email.toLowerCase()}","password":"$contrasena","surnameA":"$apellidoP","surnameB":"$apellidoM","nameOfUser":"$nombre","typeOfUser":"Consumer","status":"false","creationDate":"xx-xx-xxxx","dp":$dp,"addressU":"0x9d81a60153d369fc99192f88c04E19f5475a4cA2","typeOfOperation":"create","nameOfOperation":"createConsumer","gas":"900000"}"""
        var jsonOsman =
            """{ "email":"${email.toLowerCase()}","password":"$contrasena","surnameA":"$apellidoP","surnameB":"$apellidoM","nameOfUser":"$nombre","typeOfUser":"Consumer","status":"false","creationDate":"xx-xx-xxxx","addressU":"0xe5488d0a914c780381f150d13289170423e1510f","gas":"900000","typeOfOperation":"create","nameOfOperation":"createConsumer" }"""

        Log.d("el string", "$jsonOsman")
        var objeto = JSONObject(jsonOsman)
        Log.d("objeto json", objeto.toString())
        var hash = MD5().md5(objeto.toString())
        Log.d("el hash", "$hash")

        //return MyApi().Registrarse("$email", "$contrasena", "$apellidoP", "$apellidoM", "$nombre", "Consumer","false", "xx-xx-xxxx", "$dp", "0xf26F59ac3801F419Abe44CA7d56FfC8fB8142BbB", "create", "createConsumer", "$hash")
        return APIAmazon().Registrarse(
            "$email",
            "$contrasena",
            "$apellidoP",
            "$apellidoM",
            "$nombre",
            "Consumer",
            "false",
            "$dp",
            "create",
            "createConsumer",
            "$hash",
        )
    }

    suspend fun userRegistroFacebook(nombre: String, apellidoP: String, apellidoM: String, email: String, contrasena: String, dp: String ): Response<CreacionConsumidor> {
        Log.e("AmazonRepository", "userRegistroFacebook")

        var jsonOsman =
            """{"email":"${email.toLowerCase()}","password":"$contrasena","surnameA":"$apellidoP","surnameB":"$apellidoM","nameOfUser":"$nombre","typeOfUser":"Consumer","status":"false","typeOfOperation":"create","nameOfOperation":"createConsumer","dp":$dp}"""

        Log.d("el string", "$jsonOsman")
        var objeto = JSONObject(jsonOsman)
        Log.d("objeto json", objeto.toString())
        var hash = MD5().md5(objeto.toString())
        Log.d("el hash", "$hash")

        //return MyApi().Registrarse("$email", "$contrasena", "$apellidoP", "$apellidoM", "$nombre", "Consumer","false", "xx-xx-xxxx", "$dp", "0xf26F59ac3801F419Abe44CA7d56FfC8fB8142BbB", "create", "createConsumer", "$hash")
        return APIAmazon().RegistrarseFacebook(
            "$email",
            "$contrasena",
            "$apellidoP",
            "$apellidoM",
            "$nombre",
            "Consumer",
            "false",
            "$dp",
            "create",
            "createConsumer",
            "$hash",
            true
        )
    }
}