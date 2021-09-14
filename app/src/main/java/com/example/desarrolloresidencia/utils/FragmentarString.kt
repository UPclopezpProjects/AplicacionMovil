package com.example.desarrolloresidencia.utils

import android.graphics.Point
import android.util.Log
import com.google.android.gms.maps.model.LatLng

class FragmentarString {
     fun separaLL(coordenadas: String): LatLng {
         //Log.e("FragmentarString/coordenadas", coordenadas)
        val latlong = coordenadas.split(", ".toRegex()).toTypedArray()
         //Log.e("FragmentarString/latitude",latlong[0])
        val latitude = latlong[0].toDouble()
         //Log.e("FragmentarString/longitud",latlong[0])
        val longitude = latlong[1].toDouble()
         //Log.d("las cordenadas", latitude.toString()+" "+longitude.toString())
        return LatLng(latitude, longitude)
    }
}