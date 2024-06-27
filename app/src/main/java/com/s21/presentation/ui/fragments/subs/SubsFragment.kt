package com.s21.presentation.ui.fragments.subs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.s21.ticketsapp.databinding.FragmentSubsBinding

class SubsFragment : Fragment() {

    private var _binding: FragmentSubsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentSubsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSubs
        textView.text = "Я заглушка для ПОДПИСКИ"

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}