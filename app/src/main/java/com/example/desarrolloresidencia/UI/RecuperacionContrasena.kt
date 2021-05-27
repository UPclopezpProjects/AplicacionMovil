package com.example.desarrolloresidencia.UI

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.desarrolloresidencia.Network.model.MessageError.Error
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.ViewModel.LoginViewModel
import com.example.desarrolloresidencia.ViewModel.RecuperarPVM
import com.example.desarrolloresidencia.databinding.ActivityLoginBinding
import com.example.desarrolloresidencia.databinding.ActivityRecuperacionContrasenaBinding
import com.example.desarrolloresidencia.utils.Auth.AuthRecuperar
import com.example.desarrolloresidencia.utils.Corutinas.Coroutines
import com.example.desarrolloresidencia.utils.Corutinas.CoroutinesRecCont
import com.example.desarrolloresidencia.utils.ValidarR
import com.google.gson.Gson
import java.util.regex.Pattern


class RecuperacionContrasena : AppCompatActivity(), AuthRecuperar {
    lateinit var loginViewModel: RecuperarPVM
    private lateinit var binding: ActivityRecuperacionContrasenaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_recuperacion_contrasena)
        binding = ActivityRecuperacionContrasenaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel= ViewModelProviders.of(this).get(RecuperarPVM::class.java)
        loginViewModel.authListener = this

        //CoroutinesRecCont.authListener= this

        binding.BTEnviar.setOnClickListener {
            if (ValidarR.hayRed(this)){
                //Toast.makeText(this, "Hay red", Toast.LENGTH_SHORT).show()
                ValidationE()
            } else {
                //Toast.makeText(this, "No hay red", Toast.LENGTH_SHORT).show()
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error").setIcon(R.drawable.logo)
                builder.setMessage("No hay red")
                builder.setPositiveButton("ok"){dialog, id ->}
                builder.show()
            }
        }

        binding.BTVolver.setOnClickListener{
            finish()
        }

    }

    private fun ValidationE(){
        try {
            var email = findViewById<EditText>(R.id.ETCorreo)

            if (!validarEmail("${email.text.toString()}")){
                email.error ="Email no válido"
                email.requestFocus()
                return
            }

            loginViewModel.email = email.text.toString()
            loginViewModel.recuperar()
        } catch (e: Exception){
            Log.e("Error UI", "$e")
        }
    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    override fun onStarted() {
        binding.PB.visibility = View.VISIBLE
        binding.BTEnviar.isEnabled = false
        binding.ETCorreo.isEnabled = false
    }

    override fun onSuccess(message: String) {
        binding.PB.visibility = View.INVISIBLE
        binding.BTEnviar.isEnabled = true
        binding.ETCorreo.isEnabled = true
        //Toast.makeText(applicationContext, "$message", Toast.LENGTH_SHORT).show()
        mensajeS(message)
    }

    override fun onFailure(message: String) {
        binding.PB.visibility = View.INVISIBLE
        binding.BTEnviar.isEnabled = true
        binding.ETCorreo.isEnabled = true
        //Toast.makeText(applicationContext, "$message", Toast.LENGTH_SHORT).show()
        mensajeE(message)
    }

    fun mensajeS(mensaje : String){
        var testModel = Gson().fromJson(mensaje, Error::class.java)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Registro Exitoso").setIcon(R.drawable.logo)
        builder.setMessage("${testModel.message}")
        builder.setPositiveButton("ok"){dialog, id ->}
        builder.show()
    }

    fun mensajeE(mensaje : String){
        var testModel = Gson().fromJson(mensaje, Error::class.java)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error").setIcon(R.drawable.logo)
        builder.setMessage("${testModel.message}")
        builder.setPositiveButton("ok"){dialog, id ->}
        builder.show()
    }
}