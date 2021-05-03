package com.example.desarrolloresidencia.UI

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.desarrolloresidencia.Network.model.Login.User
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.ViewModel.LoginViewModel
import com.example.desarrolloresidencia.databinding.ActivityLoginBinding
import com.example.desarrolloresidencia.utils.Auth.AuthListener
import com.example.desarrolloresidencia.utils.Corutinas.Coroutines
import com.example.desarrolloresidencia.utils.ValidarR
import java.lang.Exception


class Login : AppCompatActivity(), AuthListener {
    lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel= ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.authListener = this

        Coroutines.authListener= this

        binding.BTLogin.setOnClickListener {
            if (ValidarR.hayRed(this)){
                ValidationE()
            } else {
                Toast.makeText(this, "No hay red", Toast.LENGTH_SHORT).show()
            }
        }


        binding.BTRegistro.setOnClickListener {
            val intent: Intent = Intent(applicationContext, RegistroUsuario::class.java)
            startActivity(intent)
        }


        binding.BTSaltar.setOnClickListener {
            val intent: Intent = Intent(applicationContext, ScannerQR::class.java)
            startActivity(intent)
        }

        binding.BTRecuperar.setOnClickListener {
            val intent: Intent = Intent(applicationContext, RecuperacionContrasena::class.java)
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
        binding.PB.visibility = VISIBLE
        binding.BTRegistro.isEnabled= false
        binding.BTSaltar.isEnabled= false
        binding.BTLogin.isEnabled= false
        binding.ETEmail.isEnabled= false
        binding.password.isEnabled= false

    }

    override fun onSuccess(message: Boolean, token: String, user: User) {
        //Toast.makeText(this, "$message $token $user", Toast.LENGTH_SHORT).show()
        binding.PB.visibility = GONE
        binding.BTRegistro.isEnabled= true
        binding.BTSaltar.isEnabled= true
        binding.ETEmail.isEnabled= true
        binding.password.isEnabled= true
        binding.BTLogin.isEnabled= true
        validarStatus(user.status)
    }

    override fun onFailure(message: String) {
        binding.PB.visibility = GONE
        binding.BTRegistro.isEnabled= true
        binding.BTSaltar.isEnabled= true
        binding.ETEmail.isEnabled= true
        binding.password.isEnabled= true
        binding.BTLogin.isEnabled= true
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun validarStatus (status : String){
        
        if (status=="false"){
            Toast.makeText(applicationContext, "Verifica tu correo electrónico", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext, "Tu correo electrónico está verificado", Toast.LENGTH_SHORT).show()
            var pasar:Intent = Intent(applicationContext, ScannerQR::class.java)
            startActivity(pasar)
        }
    }
}