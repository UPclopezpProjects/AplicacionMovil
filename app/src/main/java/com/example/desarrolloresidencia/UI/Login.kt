package com.example.desarrolloresidencia.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.desarrolloresidencia.Network.model.Login.User
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.ViewModel.LoginViewModel
import com.example.desarrolloresidencia.utils.Auth.AuthListener
import com.example.desarrolloresidencia.utils.ValidarR
import java.lang.Exception


class Login : AppCompatActivity(), AuthListener {
    lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel= ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.authListener = this


        var logear = findViewById<Button>(R.id.BTLogin)
        var registro = findViewById<Button>(R.id.BTRegistro)
        var saltar = findViewById<Button>(R.id.BTSaltar)

        logear.setOnClickListener {

                if (ValidarR.hayRed(this)){
                    //Toast.makeText(this, "Hay red", Toast.LENGTH_SHORT).show()
                    ValidationE()
                } else {
                    Toast.makeText(this, "No hay red", Toast.LENGTH_SHORT).show()
                }
        }

        registro.setOnClickListener {
            val intent: Intent = Intent(applicationContext, RegistroUsuario::class.java)
            startActivity(intent)

        }

        saltar.setOnClickListener {
            val intent: Intent = Intent(applicationContext, ScannerQR::class.java)
            startActivity(intent)
        }


    }

    private fun ValidationE(){
        try {
            var email = findViewById<EditText>(R.id.ETEmail)
            var contraseña = findViewById<EditText>(R.id.password)

            if (email.text.toString() == "") {
                email.error = "Ingresa el correo"
                email.requestFocus()
                return
            }

            if (contraseña.text.toString() == "") {
                contraseña.error = "Ingresa la contraseña"
                contraseña.requestFocus()
                return
            }
            loginViewModel.email = email.text.toString()
            loginViewModel.password = contraseña.text.toString()

            loginViewModel.onLoginButtonClick()
        } catch (e: Exception){
            Log.e("Error UI", "$e")
        }
    }

    override fun onStarted() {
    }

    override fun onSuccess(message: Boolean, token: String, user: User) {
        //Toast.makeText(this, "$message $token $user", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "ACCESO CORRECTO", Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
<<<<<<< Updated upstream
=======

    fun validarStatus (status : String){
        
        if (status=="false"){
            Toast.makeText(applicationContext, "Verifica tu correo electrónico", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext, "Tu correo electrónico está verificado", Toast.LENGTH_SHORT).show()
            var pasar:Intent = Intent(applicationContext, ScannerQR::class.java)
            startActivity(pasar)
        }
    }
>>>>>>> Stashed changes
}