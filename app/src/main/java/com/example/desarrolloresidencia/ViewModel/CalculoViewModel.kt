package com.example.desarrolloresidencia.ViewModel

import androidx.lifecycle.ViewModel

class CalculoViewModel():ViewModel() {
    var peso:Double ?= null

    var grasasT:Double ?= null
    var colesterol:Double ?= null
    var sodio:Double ?= null
    var potasio:Double ?= null
    var hidratosC:Double ?= null
    var azucares:Double ?= null
    var proteina:Double ?= null
    var agua:Double ?= null
    var vitA:Double ?= null
    var vitC:Double ?= null
    var vitE:Double ?= null
    var vitK:Double ?= null
    var calcio:Double ?= null
    var hierro:Double ?= null
    var magnesio:Double ?= null
    var manganeso:Double ?= null
    var fosforo:Double ?= null
    var zinc:Double ?= null

    fun grasasTotales() {
        //return (peso!! *15)/100
        grasasT = (peso!! *15)/100
    }

    fun colesterol() {
        //return (peso!! *0)/100
        colesterol = (peso!! *0)/100
    }

    fun sodio() {
        //return (peso!! *7)/100
        sodio = (peso!! *7)/100
    }

    fun potasio() {
        //return (peso!! *485)/100
        potasio = (peso!! *485)/100
    }

    fun hidratosC() {
        //return (peso!! *9)/100
        hidratosC = (peso!! *9)/100
    }

    fun azucares() {
        //return (peso!! *0.7)/100
        azucares = (peso!! *0.7)/100
    }

    fun proteina() {
        //return (peso!! *2)/100
        proteina = (peso!! *2)/100
    }

    fun agua() {
        //return (peso!! *73.23)/100
        agua = (peso!! *73.23)/100
    }

    fun vitA() {
        //return (peso!! *146)/100
        vitA = (peso!! *146)/100
    }

    fun vitC() {
        //return (peso!! *10)/100
        vitC = (peso!! *10)/100
    }

    fun vitE() {
        //return (peso!! *2.07)/100
        vitE = (peso!! *2.07)/100
    }

    fun vitK() {
        //return (peso!! *21)/100
        vitK = (peso!! *21)/100
    }

    fun calcio() {
        //return (peso!! *12)/100
        calcio = (peso!! *12)/100
    }

    fun hierro() {
        //return (peso!! *0.6)/100
        hierro =(peso!! *0.6)/100
    }

    fun magnesio() {
        //return (peso!! *29)/100
        magnesio = (peso!! *29)/100
    }

    fun manganeso() {
        //return (peso!! *0.142)/100
        manganeso = (peso!! *0.142)/100
    }

    fun fosforo() {
        //return (peso!! *52)/100
        fosforo = (peso!! *52)/100
    }

    fun zinc() {
        //return (peso!! *0.64)/100
        zinc = (peso!! *0.64)/100
    }
}