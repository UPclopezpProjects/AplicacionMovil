package com.example.desarrolloresidencia.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.example.desarrolloresidencia.Network.model.CreationC.User
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.ViewModel.LoginViewModel
import com.example.desarrolloresidencia.ViewModel.RegistroViewModel
import com.example.desarrolloresidencia.utils.Auth.AuthRegistro
import com.example.desarrolloresidencia.utils.ValidarR

class RegistroUsuario : AppCompatActivity(), AuthRegistro {

    lateinit var registroViewModel: RegistroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        registroViewModel= ViewModelProviders.of(this).get(RegistroViewModel::class.java)
        registroViewModel.authListener = this

        var registro = findViewById<Button>(R.id.BTRegistrar)

        registro.setOnClickListener {

            if (ValidarR.hayRed(this)){
                //Toast.makeText(this, "Hay red", Toast.LENGTH_SHORT).show()
                ValidationE()
            } else {
                Toast.makeText(this, "No hay red", Toast.LENGTH_SHORT).show()
            }
        }

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

        registroViewModel.nombre = nombre.text.toString()
        registroViewModel.apellidoP = apellidoP.text.toString()
        registroViewModel.apellidoM = apellidoM.text.toString()
        registroViewModel.email = username.text.toString()
        registroViewModel.password = contrasena.text.toString()

        registroViewModel.onLoginButtonClick()

    }

    override fun onStarted() {
    }

    override fun onSuccess(message: Boolean, token: String, user: User) {
        //Toast.makeText(applicationContext, "$message", Toast.LENGTH_SHORT).show()
        Toast.makeText(applicationContext, "Usuario Registrado", Toast.LENGTH_SHORT).show()
    }


    override fun onFailure(message: String) {
        Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
    }


}