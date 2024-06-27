package com.s21.presentation.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.s21.ticketsapp.databinding.FragmentCorocheBinding


class CorocheFragment : Fragment() {

    private var _binding: FragmentCorocheBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(CorocheViewModel::class.java)

        _binding = FragmentCorocheBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCoroche
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}