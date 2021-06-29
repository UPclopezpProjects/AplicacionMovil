package com.example.desarrolloresidencia.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MD5 {

    fun md5(s: String): String? {
        try {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(s.toByteArray())).toString(16).padStart(32, '0')
        } catch (e: NoSuchAlgorithmException){
            return e.toString()
        }

    }

}