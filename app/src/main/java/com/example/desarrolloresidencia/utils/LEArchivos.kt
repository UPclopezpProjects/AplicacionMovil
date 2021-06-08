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
        fun sobrescribir(texto: String, baseContext: Context){
            try {
                val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
                val miCarpeta = File(rutaSD, "datos")
                if (!miCarpeta.exists()) {
                    miCarpeta.mkdir()
                }
                val ficheroFisico = File(miCarpeta, "datos.txt")
                //esta agrega texto al archivo
                //ficheroFisico.appendText("$texto\n")
                //esto sobreescribe texto al archivo
                ficheroFisico.writeText("$texto")
                //Toast.makeText(baseContext, "Creado correctamente $ficheroFisico", Toast.LENGTH_SHORT).show()
            } catch (e: Exception){
                Log.e("ERROR", "$e")
            }
        }

        fun guardar(texto: String, baseContext: Context){
            try {
                val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
                val miCarpeta = File(rutaSD, "datos")
                if (!miCarpeta.exists()) {
                    miCarpeta.mkdir()
                }
                val ficheroFisico = File(miCarpeta, "datos.txt")
                //esta agrega texto al archivo
                ficheroFisico.appendText("$texto\n")
                //Toast.makeText(baseContext, "Creado correctamente $ficheroFisico", Toast.LENGTH_SHORT).show()
            } catch (e: Exception){
                Log.e("ERROR", "$e")
            }
        }

        fun Cargar(baseContext: Context):String{
            val rutaSD = baseContext
                    .getExternalFilesDir(null)?.absolutePath
            val miCarpeta = File(rutaSD, "datos")
            val ficheroFisico = File(miCarpeta, "datos.txt")

            if (!miCarpeta.exists()) {
                Log.d("LEArchivos", "no existía la carpeta")
                miCarpeta.mkdir()
            }
            if (!ficheroFisico.exists()) {
                Log.d("LEArchivos", "no existe el archivo")
                ficheroFisico.createNewFile()
                sobrescribir("""{"createAdministrator":false,"createTUser":false,"updateMe":true,"updateAdministrator":false,"updateTUser":false,"deleteMe":true,"deleteAdministrator":false,"deleteTUser":false,"readMe":true,"readAdministrator":false,"readTUser":false,"loginUser":true}""", baseContext)
            }

            val fichero = BufferedReader(InputStreamReader(
                    FileInputStream(ficheroFisico)))
            val texto = fichero.use(BufferedReader::readText)
            return texto
        }

        fun permisos(contexto: Context, actividad:Activity){
            if ((ContextCompat.checkSelfPermission(contexto, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(contexto, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)){
                ActivityCompat.requestPermissions(actividad, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 123)
            }
        }
    }
}


