package com.example.desarrolloresidencia.UI

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.desarrolloresidencia.Network.model.Login.User
import com.example.desarrolloresidencia.Network.model.MessageError.Error
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.ViewModel.ActualizarViewModel
import com.example.desarrolloresidencia.databinding.ActivityModificacionUsuarioBinding
import com.example.desarrolloresidencia.utils.Auth.AuthListener
import com.example.desarrolloresidencia.utils.ValidarR
import com.example.desarrolloresidencia.utils.responseUser
import com.google.gson.Gson

class ModificacionUsuario : AppCompatActivity(), AuthListener {
    lateinit var modificacionViewModel: ActualizarViewModel
    private lateinit var binding: ActivityModificacionUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_modificacion_usuario)
        binding = ActivityModificacionUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        modificacionViewModel= ViewModelProviders.of(this).get(ActualizarViewModel::class.java)
        modificacionViewModel.authListener = this

        rellenarCampos()

        binding.BTRegistrar.setOnClickListener {

            if (ValidarR.hayRed(this)){
                ValidationE()
            } else {
                //Toast.makeText(this, "No hay red", Toast.LENGTH_SHORT).show()
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error Login").setIcon(R.drawable.logo)
                builder.setMessage("No hay red")
                builder.setPositiveButton("ok"){dialog, id ->}
                builder.show()
            }
        }

        binding.BTVolver.setOnClickListener{
            finish()
        }
    }

    fun ValidationE(){
        var nombre = findViewById<EditText>(R.id.ETNombre)
        var apellidoP = findViewById<EditText>(R.id.ETApellidoP)
        var apellidoM = findViewById<EditText>(R.id.ETApellidoM)
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

        if (contrasena.text.toString() ==""){
            contrasena.error = "Ingresa la contraseña"
            contrasena.requestFocus()
            return
        }

        modificacionViewModel.password = contrasena.text.toString()
        modificacionViewModel.nombre = nombre.text.toString()
        modificacionViewModel.apellidoP = apellidoP.text.toString()
        modificacionViewModel.apellidoM = apellidoM.text.toString()

        modificacionViewModel.actualizar()
    }

    override fun onStarted() {
        binding.PB.visibility = View.VISIBLE
        binding.ETNombre.isEnabled= false
        binding.ETApellidoP.isEnabled= false
        binding.ETApellidoM.isEnabled= false
        binding.ETContrasena.isEnabled= false
        //binding.ETEmail.isEnabled= false
    }

    override fun onSuccess(message: Boolean, token: String, user: User) {
        binding.PB.visibility = View.GONE
        binding.ETNombre.isEnabled= true
        binding.ETApellidoP.isEnabled= true
        binding.ETApellidoM.isEnabled= true
        binding.ETContrasena.isEnabled= true
        //binding.ETEmail.isEnabled= true
        validarStatus(user.status)
    }

    override fun onFailure(message: String) {
        binding.PB.visibility = View.GONE
        binding.ETNombre.isEnabled= true
        binding.ETApellidoP.isEnabled= true
        binding.ETApellidoM.isEnabled= true
        binding.ETContrasena.isEnabled= true
        //binding.ETEmail.isEnabled= true
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        mensajeE(message)
    }

    fun validarStatus (status : String){
        if (status=="false"){
            //Toast.makeText(applicationContext, "Verifica tu correo electrónico", Toast.LENGTH_SHORT).show()
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Mensaje del servidor").setIcon(R.drawable.logo)
            builder.setMessage("Verifica tu correo electrónico")
            builder.setPositiveButton("ok"){dialog, id ->}
            builder.show()
        }else{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Modificación correcta").setIcon(R.drawable.logo)
            builder.setMessage("Se ha modificado tu perfil satisfactoriamente")
            builder.setPositiveButton("ok"){dialog, id ->}
            builder.show()
        }
    }

    fun mensajeE(mensaje : String){
        var testModel = Gson().fromJson(mensaje, Error::class.java)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Mensaje del servidor").setIcon(R.drawable.logo)
        builder.setMessage("${testModel.message}")
        builder.setPositiveButton("ok"){dialog, id ->}
        builder.show()
    }

    fun rellenarCampos(){
        if (responseUser.user?.nameOfUser.toString() != null){
            binding.ETNombre.setText(responseUser.user?.nameOfUser.toString())
        }else{
            binding.ETNombre.setText("")
        }

        if (responseUser.user?.surnameA.toString() != null && responseUser.user?.surnameA.toString() != " "){
            binding.ETApellidoP.setText(responseUser.user?.surnameA.toString())
        }else{
            binding.ETApellidoP.setText("")
        }

        if (responseUser.user?.surnameB.toString() != null && responseUser.user?.surnameB.toString() != " "){
            binding.ETApellidoM.setText(responseUser.user?.surnameB.toString())
        }else{
            binding.ETApellidoM.setText("")
        }
    }
}