package com.example.desarrolloresidencia.UI.ayudas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.desarrolloresidencia.R

class ayudaIcono: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.ayudaicono, container, false)
        return vista
    }
}