package com.s21.presentation.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.s21.ticketsapp.databinding.FragmentOtelsBinding

class OtelsFragment : Fragment() {

    private var _binding: FragmentOtelsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentOtelsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard

        textView.text = "Здесь заглушка для ОТЕЛИ"

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}