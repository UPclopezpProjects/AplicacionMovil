package com.example.desarrolloresidencia.Network.model.Trazabilidad

data class Message(
    val __v: Int,
    val _id: String,
    val code: String,
    val currentStage: String,
    val description: String,
    val destination: String,
    val fid: String,
    val id: String,
    val image: String,
    val name: String,
    val origin: String,
    val previousStage: String,
    val ubication: String,
    val addressTransaction: String,
    val addressContract: String,
    val hash: String
)