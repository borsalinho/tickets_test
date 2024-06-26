package com.s21.presentation.ui.fragments.alltickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.s21.presentation.app.App
import com.s21.presentation.ui.adapters.ViewDataAdapter
import com.s21.presentation.ui.fragments.choiseticket.ChoiseTicketViewModel
import com.s21.ticketsapp.R
import com.s21.ticketsapp.databinding.FragmentAllTicketsBinding
import javax.inject.Inject


class AllTicketsFragment : Fragment(){

    private var _binding: FragmentAllTicketsBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var allTicketsViewModel : AllTicketsViewModel
    @Inject lateinit var choiseTicketsViewModel : ChoiseTicketViewModel
    @Inject lateinit var viewDataAdapter: ViewDataAdapter

    private lateinit var btnback : Button
    private lateinit var textDepartDest : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllTicketsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        textDepartDest = binding.textDepartDest
        btnback = binding.btnBackToChioseTickets

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewDataAdapter
        }

        savePoints()
        getTickets()



        backOnChoiseTicketsFragment()
        errorObserve()
        return root
    }

    private fun savePoints() {
        choiseTicketsViewModel.departurePoint.observe(viewLifecycleOwner, Observer {
            allTicketsViewModel.setDeparturePoint(it)
        })

        choiseTicketsViewModel.destinationPoint.observe(viewLifecycleOwner, Observer {
            allTicketsViewModel.setDestinationPoint(it)
        })

        allTicketsViewModel.departDest.observe(viewLifecycleOwner, Observer {
            textDepartDest.text = it
        })
    }

    private fun backOnChoiseTicketsFragment(){
        btnback.setOnClickListener{
            findNavController().navigate(R.id.action_allTicketsFragment_to_choiseTicketFragment)
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
        allTicketsViewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getTickets(){
        allTicketsViewModel.getTickets()
        allTicketsViewModel.tickets.observe(viewLifecycleOwner, Observer { offers ->
            viewDataAdapter.items = offers
        })
    }

}