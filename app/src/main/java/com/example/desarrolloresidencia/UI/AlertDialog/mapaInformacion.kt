package com.example.desarrolloresidencia.UI.AlertDialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.desarrolloresidencia.R
import com.example.desarrolloresidencia.UI.ayudas.ayudaCarru
import com.example.desarrolloresidencia.UI.ayudas.ayudaIcono
import com.example.desarrolloresidencia.UI.ayudas.ayudaQR
import com.example.desarrolloresidencia.databinding.FragmentMapaInformacionBinding


class mapaInformacion : DialogFragment() {

    private var _binding: FragmentMapaInformacionBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        _binding = FragmentMapaInformacionBinding.inflate(inflater, container, false)
        val view = binding.root
        return  view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
        //val mPager = view!!.findViewById(R.id.pager) as ViewPager
        val mPagerAdapter: FragmentStatePagerAdapter = TestAdapter(childFragmentManager)
        //mPager.adapter = mPagerAdapter

        binding.pager.adapter =mPagerAdapter
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.60).toInt()
        dialog!!.window?.setLayout(width, /*ViewGroup.LayoutParams.WRAP_CONTENT*/height)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private class TestAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm!!) {
        override fun getItem(position: Int): Fragment =ayudaQR()


        override fun getCount(): Int = 1
    }
}