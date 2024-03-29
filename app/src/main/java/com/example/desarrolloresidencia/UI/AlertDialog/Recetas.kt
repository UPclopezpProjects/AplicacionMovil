package com.example.desarrolloresidencia.UI.AlertDialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.databinding.FragmentGuacamoleBinding
import com.example.desarrolloresidencia.databinding.FragmentRecetasBinding

class Recetas : DialogFragment() {

private var _binding: FragmentRecetasBinding?= null
private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //dialog?.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        _binding = FragmentRecetasBinding.inflate(inflater, container, false)
        val view = binding.root
        return  view
    //return inflater.inflate(R.layout.fragment_recetas, container, false)
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.35).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}