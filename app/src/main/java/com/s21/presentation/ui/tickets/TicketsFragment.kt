package com.s21.presentation.ui.tickets

import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.s21.presentation.app.App

import com.s21.ticketsapp.databinding.FragmentTicketsBinding
import javax.inject.Inject

class TicketsFragment : Fragment() {

    private var _binding: FragmentTicketsBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var ticketsViewModel : TicketsViewModel
    @Inject lateinit var application : Application

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTicketsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val departurePoint = binding.editDeparturePoint
        ticketsViewModel.departurePoint.observe(viewLifecycleOwner, Observer {
            if (departurePoint.text.toString() != it) {
                departurePoint.setText(it)
            }
        })
        SaveOnSharedPreferences(departurePoint)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun SaveOnSharedPreferences(departurePoint : EditText){
        departurePoint.addTextChangedListener(object :  TextWatcher{

            override fun afterTextChanged(s: Editable?) {
                ticketsViewModel.setDeparturePoint(s.toString())
                ticketsViewModel.saveOnSharedPreferences(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //
            }
        })
    }
}