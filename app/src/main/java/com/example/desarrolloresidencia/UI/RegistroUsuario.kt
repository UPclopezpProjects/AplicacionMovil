package com.example.desarrolloresidencia.UI

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.desarrolloresidencia.Network.model.CreationC.User
import com.example.desarrolloresidencia.Network.model.MessageError.Error
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.ViewModel.RegistroViewModel
import com.example.desarrolloresidencia.databinding.ActivityRegistroUsuarioBinding
import com.example.desarrolloresidencia.utils.Auth.AuthRegistro
import com.example.desarrolloresidencia.utils.ValidarR
import com.google.gson.Gson
import java.util.regex.Pattern

class RegistroUsuario : AppCompatActivity(), AuthRegistro {

    lateinit var registroViewModel: RegistroViewModel
    private lateinit var binding:ActivityRegistroUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroUsuarioBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_registro_usuario)
        setContentView(binding.root)

        registroViewModel= ViewModelProviders.of(this).get(RegistroViewModel::class.java)
        registroViewModel.authListener = this
        registroViewModel.contexto = this

        //CoroutinesRU.authListener= this

        binding.BTRegistrar.setOnClickListener {
            if (ValidarR.hayRed(this)){
                ValidationE()
            } else {
                //Toast.makeText(this, "No hay red", Toast.LENGTH_SHORT).show()
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error login").setIcon(R.drawable.logo)
                builder.setMessage("No hay red")
                builder.setPositiveButton("ok"){dialog, id ->}
                builder.show()
            }
        }

        binding.BTVolver.setOnClickListener{
            finish()
        }

    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun ValidationE(){
        var nombre = findViewById<EditText>(R.id.ETNombre)
        var apellidoP = findViewById<EditText>(R.id.ETApellidoP)
        var apellidoM = findViewById<EditText>(R.id.ETApellidoM)
        var username = findViewById<EditText>(R.id.ETEmail)
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

        if (!validarEmail("${username.text.toString()}")){
            username.error ="Email no válido"
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
        binding.PB.visibility = View.VISIBLE
        binding.ETNombre.isEnabled = false
        binding.ETApellidoP.isEnabled = false
        binding.ETApellidoM.isEnabled = false
        binding.ETEmail.isEnabled = false
        binding.ETContrasena.isEnabled = false
        binding.BTRegistrar.isEnabled = false
    }

    override fun onSuccess(message: Boolean) {
        binding.PB.visibility = View.INVISIBLE
        binding.ETNombre.isEnabled = true
        binding.ETApellidoP.isEnabled = true
        binding.ETApellidoM.isEnabled = true
        binding.ETEmail.isEnabled = true
        binding.ETContrasena.isEnabled = true
        binding.BTRegistrar.isEnabled = true
        Log.d("respuesta correcta", "$message")
        //Toast.makeText(applicationContext, "Usuario Registrado", Toast.LENGTH_SHORT).show()
        //Toast.makeText(applicationContext, registroViewModel.validarStatus(user.status), Toast.LENGTH_SHORT).show()

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Registro satisfactorio").setIcon(R.drawable.logo)
        builder.setMessage("Verifica tu correo electrónico")
        builder.setPositiveButton("ok"){dialog, id ->}
        builder.show()

        binding.ETNombre.setText("")
        binding.ETApellidoP.setText("")
        binding.ETApellidoM.setText("")
        binding.ETEmail.setText("")
        binding.ETContrasena.setText("")
    }


    override fun onFailure(message: String) {
        binding.PB.visibility = View.INVISIBLE
        binding.ETNombre.isEnabled = true
        binding.ETApellidoP.isEnabled = true
        binding.ETApellidoM.isEnabled = true
        binding.ETEmail.isEnabled = true
        binding.ETContrasena.isEnabled = true
        binding.BTRegistrar.isEnabled = true
        Log.e("Error en registro", message)
        //Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()

        mensajeE(message)
    }

    fun mensajeE(mensaje : String){
        var testModel = Gson().fromJson(mensaje, Error::class.java)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Mensaje del servidor").setIcon(R.drawable.logo)
        builder.setMessage("${testModel.message}")
        builder.setPositiveButton("ok"){dialog, id ->}
        builder.show()
    }

}