package com.example.desarrolloresidencia.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.desarrolloresidencia.UI.ScannerQR
import com.google.zxing.integration.android.IntentIntegrator
import java.io.*
import java.lang.Exception

class LEArchivos {
    companion object{

        var nombreIP = "archivo.txt"

        fun sobrescribir(texto: String, baseContext: Context){
            try {
                val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
                val miCarpeta = File(rutaSD, "datos")
                if (!miCarpeta.exists()) {
                    miCarpeta.mkdir()
                }
                val ficheroFisico = File(miCarpeta, "datos.txt")
                ficheroFisico.writeText("$texto")
            } catch (e: Exception){
                Log.e("ERROR", "$e")
            }
        }

        fun Cargar(baseContext: Context):String{
            val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
            val miCarpeta = File(rutaSD, "datos")
            val ficheroFisico = File(miCarpeta, "datos.txt")

            if (!miCarpeta.exists()) {
                Log.d("LEArchivos", "no existía la carpeta")
                miCarpeta.mkdir()
            }
            if (!ficheroFisico.exists()) {
                Log.d("LEArchivos", "no existe el archivo")
                ficheroFisico.createNewFile()
                sobrescribir("""{"createAdministrator": false, "createTUser": false, "createData": false, "updateMe": true, "updateAdministrator": false, "updateTUser": false, "updateData": false, "deleteMe": true, "deleteAdministrator": false, "deleteTUser": false, "deleteData": false, "readMe": true, "readAdministrator": false, "readTUser": false, "readData": false, "loginUser": true}""", baseContext)
            }

            val fichero = BufferedReader(InputStreamReader(
                    FileInputStream(ficheroFisico)))
            val texto = fichero.use(BufferedReader::readText)
            return texto
        }

        //los siguientes métodos son para almacenar la información de la IP
        fun sobrescribirIP(texto: String, baseContext: Context){
            //Toast.makeText(baseContext, "LEArchivos/sobreescribirIP/texto: $texto", Toast.LENGTH_LONG).show()
            try {
                val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
                val miCarpeta = File(rutaSD, "datos")
                if (!miCarpeta.exists()) {
                    miCarpeta.mkdir()
                }
                val ficheroFisico = File(miCarpeta, nombreIP)
                ficheroFisico.writeText("$texto")
            } catch (e: Exception){
                Log.d("ERROR", "$e")
            }
        }

        fun CargarIP(baseContext: Context):String{
            val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
            val miCarpeta = File(rutaSD, "datos")
            val ficheroFisico = File(miCarpeta, nombreIP)

            if (!miCarpeta.exists()) {
                Log.d("LEArchivos/", "no existía la carpeta")
                miCarpeta.mkdir()
            }
            if (!ficheroFisico.exists()) {
                Log.d("LEArchivos", "no existe el archivo")
                ficheroFisico.createNewFile()
                sobrescribirIP("52.202.214.13", baseContext)
            }

            val fichero = BufferedReader(InputStreamReader(
                FileInputStream(ficheroFisico)))
            val texto = fichero.use(BufferedReader::readText)
            //Toast.makeText(baseContext, "LEArchivos/CargarIP/texto: $texto", Toast.LENGTH_LONG).show()
            return texto
        }
    }
}