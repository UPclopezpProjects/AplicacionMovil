package com.example.desarrolloresidencia.Network.model.GetLog

data class Log(
    val address: String,
    val blockHash: String,
    val blockNumber: Int,
    val event: String,
    val id: String,
    val logIndex: Int,
    val raw: Raw,
    val removed: Boolean,
    val returnValues: ReturnValues,
    val signature: String,
    val transactionHash: String,
    val transactionIndex: Int,
    val type: String
)