package com.example.desarrolloresidencia.UI.AlertDialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.databinding.FragmentDetallesAddressBinding
import com.example.desarrolloresidencia.databinding.FragmentMapaInformacionBinding

class detallesAddress() : DialogFragment() {

    private var _binding: FragmentDetallesAddressBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        _binding = FragmentDetallesAddressBinding.inflate(inflater, container, false)
        val view = binding.root

        var transaction = getArguments()?.getString("transaction");
        var contract = getArguments()?.getString("contract")
        var hash = getArguments()?.getString("hash")
        binding.TVAddressT.setText(transaction)
        binding.TVAddressC.setText(contract)
        binding.TVAddresH.setText(hash)

        return view
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.60).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT/*height*/)
    }


}