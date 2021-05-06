package com.example.desarrolloresidencia.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.ViewModel.ActualizarViewModel
import com.example.desarrolloresidencia.ViewModel.CalculoViewModel
import com.example.desarrolloresidencia.ViewModel.LoginViewModel
import com.example.desarrolloresidencia.databinding.ActivityCalculoNutricionalBinding
import com.example.desarrolloresidencia.databinding.ActivityModificacionUsuarioBinding

class CalculoNutricional : AppCompatActivity() {

    lateinit var modificacionVM: CalculoViewModel
    private lateinit var binding: ActivityCalculoNutricionalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_calculo_nutricional)
        binding = ActivityCalculoNutricionalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        modificacionVM= ViewModelProviders.of(this).get(CalculoViewModel::class.java)

       if (modificacionVM.grasasT != null){
           binding.TVGrasasT.setText("${modificacionVM.grasasT}g")
       }

        if (modificacionVM.colesterol != null){
            binding.TVColesterol.setText("${modificacionVM.colesterol}mg")
        }

        if (modificacionVM.sodio != null){
            binding.TVSodio.setText("${modificacionVM.sodio}mg")
        }

        if (modificacionVM.potasio != null){
            binding.TVPotasio.setText("${modificacionVM.potasio}mg")
        }

        if (modificacionVM.hidratosC != null){
            binding.TVHidratosC.setText("${modificacionVM.hidratosC}g")
        }

        if (modificacionVM.azucares != null){
            binding.TVAzucares.setText("${modificacionVM.azucares}g")
        }

        if (modificacionVM.proteina != null){
            binding.TVProteina.setText("${modificacionVM.proteina}g")
        }

        if (modificacionVM.agua != null){
            binding.TVAgua.setText("${modificacionVM.agua}g")
        }

        if (modificacionVM.vitA != null){
            binding.TVVitA.setText("${modificacionVM.vitA}IU")
        }

        if (modificacionVM.vitC != null){
            binding.TVVitC.setText("${modificacionVM.vitC}mg")
        }

        if (modificacionVM.vitE != null){
            binding.TVVitE.setText("${modificacionVM.vitE}mg")
        }

        if (modificacionVM.vitK != null){
            binding.TVVitK.setText("${modificacionVM.vitK}mcg")
        }

        if (modificacionVM.calcio != null){
            binding.TVCalcio.setText("${modificacionVM.calcio}mg")
        }

        if (modificacionVM.hierro != null){
            binding.TVHierro.setText("${modificacionVM.hierro}mg")
        }

        if (modificacionVM.magnesio != null){
            binding.TVMagnesio.setText("${modificacionVM.magnesio}mg")
        }

        if (modificacionVM.manganeso != null){
            binding.TVManganeso.setText("${modificacionVM.manganeso}g")
        }

        if (modificacionVM.fosforo != null){
            binding.TVFosforo.setText("${modificacionVM.fosforo}mg")
        }

        if (modificacionVM.zinc != null){
            binding.TVZinc.setText("${modificacionVM.zinc}mg")
        }

        binding.BTCalcular.setOnClickListener{
            modificacionVM.peso =binding.ETGramos.text.toString().toDouble()
            modificacionVM.grasasTotales()
            modificacionVM.colesterol()
            modificacionVM.sodio()
            modificacionVM.potasio()
            modificacionVM.hidratosC()
            modificacionVM.azucares()
            modificacionVM.proteina()
            modificacionVM.agua()
            modificacionVM.vitA()
            modificacionVM.vitC()
            modificacionVM.vitE()
            modificacionVM.vitK()
            modificacionVM.calcio()
            modificacionVM.hierro()
            modificacionVM.magnesio()
            modificacionVM.manganeso()
            modificacionVM.fosforo()
            modificacionVM.zinc()


            binding.TVGrasasT.setText(modificacionVM.grasasT.toString()+"g")
            binding.TVColesterol.setText(modificacionVM.colesterol.toString()+"mg")
            binding.TVSodio.setText(modificacionVM.sodio.toString()+"mg")
            binding.TVPotasio.setText(modificacionVM.potasio.toString()+"mg")
            binding.TVHidratosC.setText(modificacionVM.hidratosC.toString()+"g")
            binding.TVAzucares.setText(modificacionVM.azucares.toString()+"g")
            binding.TVProteina.setText(modificacionVM.proteina.toString()+"g")
            binding.TVAgua.setText(modificacionVM.agua.toString()+"g")
            binding.TVVitA.setText(modificacionVM.vitA.toString()+"IU")
            binding.TVVitC.setText(modificacionVM.vitC.toString()+"mg")
            binding.TVVitE.setText(modificacionVM.vitE.toString()+"mg")
            binding.TVVitK.setText(modificacionVM.vitK.toString()+"mcg")
            binding.TVCalcio.setText(modificacionVM.calcio.toString()+"mg")
            binding.TVHierro.setText(modificacionVM.hierro.toString()+"mg")
            binding.TVMagnesio.setText(modificacionVM.magnesio.toString()+"mg")
            binding.TVManganeso.setText(modificacionVM.manganeso.toString()+"g")
            binding.TVFosforo.setText(modificacionVM.fosforo.toString()+"mg")
            binding.TVZinc.setText(modificacionVM.zinc.toString()+"mg")
        }

        binding.BTVolver.setOnClickListener{
            finish()
        }





    }
}