package com.example.desarrolloresidencia.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.desarrolloresidencia.R

class RegistroUsuario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        var registro = findViewById<Button>(R.id.BTRegistrar)
        registro.setOnClickListener {
            ValidationE()
        }
    }

    fun ValidationE(){
        var nombre = findViewById<EditText>(R.id.ETNombre)
        var apellidoP = findViewById<EditText>(R.id.ETApellidoP)
        var apellidoM = findViewById<EditText>(R.id.ETApellidoM)
        var username = findViewById<EditText>(R.id.ETUsername)
        var contrasena = findViewById<EditText>(R.id.ETContrasena)

        if (nombre.text.toString() ==""){
            nombre.error = "Ingresa el nombre"
            nombre.requestFocus()
            return
        }

        if (apellidoP.text.toString() ==""){
            apellidoP.error = "Ingresa el apellido paterno"
            apellidoP.requestFocus()
            return
        }

        if (apellidoM.text.toString() ==""){
            apellidoM.error = "Ingresa el apellido materno"
            apellidoM.requestFocus()
            return
        }

        if (username.text.toString() ==""){
            username.error = "Ingresa el Email"
            username.requestFocus()
            return
        }

        if (contrasena.text.toString() ==""){
            contrasena.error = "Ingresa la contraseña"
            contrasena.requestFocus()
            return
        }

        var intent: Intent = Intent(applicationContext, Login::class.java)
        startActivity(intent)

    }
}