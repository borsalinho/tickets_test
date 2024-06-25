package com.s21.presentation.ui.fragments.choiseticket

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.s21.presentation.app.App
import com.s21.presentation.ui.tickets.TicketsViewModel
import com.s21.ticketsapp.databinding.FragmentChoiseTicketBinding
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.s21.presentation.ui.adapters.ViewDataAdapter
import com.s21.ticketsapp.R

import javax.inject.Inject

class ChoiseTicketFragment : Fragment() {

    private var _binding: FragmentChoiseTicketBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var ticketsViewModel : TicketsViewModel
    @Inject lateinit var choiseTicketsViewModel : ChoiseTicketViewModel
    @Inject lateinit var viewDataAdapter: ViewDataAdapter

    private lateinit var btnback : Button
    private lateinit var departurePoint : EditText
    private lateinit var destinationPoint : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentChoiseTicketBinding.inflate(inflater, container, false)
        val root: View = binding.root

        departurePoint = binding.editDeparturePoint
        destinationPoint = binding.editDestinationPoint
        btnback = binding.btnBack

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewDataAdapter
        }

        getTicketsOffers()
        savePoints()
        setPoints()
        backOnTicketsFragment()
        errorObserve()

        return root
    }

    private fun getTicketsOffers(){
        choiseTicketsViewModel.getTicketsOffers()
        choiseTicketsViewModel.ticketsOffers.observe(viewLifecycleOwner, Observer { offers ->
            viewDataAdapter.items = offers
        })
    }

    private fun savePoints() {
        ticketsViewModel.departurePoint.observe(viewLifecycleOwner, Observer {
            choiseTicketsViewModel.setDeparturePoint(it)
        })

        ticketsViewModel.destinationPoint.observe(viewLifecycleOwner, Observer {
            choiseTicketsViewModel.setDestinationPoint(it)
        })
    }

    private fun setPoints() {
        choiseTicketsViewModel.departurePoint.observe(viewLifecycleOwner, Observer {
            if (departurePoint.text.toString() != it) {
                departurePoint.setText(it)
                departurePoint.setSelection(it.length)
            }
        })

        choiseTicketsViewModel.destinationPoint.observe(viewLifecycleOwner, Observer {
            if (destinationPoint.text.toString() != it) {
                destinationPoint.setText(it)
                destinationPoint.setSelection(it.length)
            }
        })
    }

    private fun backOnTicketsFragment(){
        btnback.setOnClickListener{
            findNavController().navigate(R.id.action_choiseTicketFragment_to_navigation_tickets)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val navView = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        navView.visibility = View.VISIBLE
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navView = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        navView.visibility = View.GONE
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun errorObserve(){
        choiseTicketsViewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
            }
        })
    }

}