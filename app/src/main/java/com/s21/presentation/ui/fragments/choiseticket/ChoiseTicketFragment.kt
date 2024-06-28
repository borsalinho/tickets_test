package com.s21.presentation.ui.fragments.choiseticket

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.s21.presentation.app.App
import com.s21.presentation.ui.tickets.TicketsViewModel
import com.s21.ticketsapp.databinding.FragmentChoiseTicketBinding
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.s21.presentation.di.AppModule
import com.s21.presentation.models.OfferViewData
import com.s21.presentation.models.TicketOfferViewData
import com.s21.presentation.models.ValueViewData
import com.s21.presentation.ui.adapters.ViewDataAdapter
import com.s21.ticketsapp.R

import javax.inject.Inject

class ChoiseTicketFragment : Fragment() {

    private var _binding: FragmentChoiseTicketBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var ticketsViewModel : TicketsViewModel
    @Inject lateinit var choiseTicketsViewModel : ChoiseTicketViewModel
    @Inject lateinit var viewDataAdapter: ViewDataAdapter

    private lateinit var btnBack : Button
    private lateinit var btnSeeAll : Button
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
        btnBack = binding.btnBack
        btnSeeAll = binding.btnSeeAll

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewDataAdapter
        }

        getTicketsOffers()
        savePoints()
        setPoints()
        backOnTicketsFragment()
        backShowAllTickets()
        errorObserve()

        return root
    }

    private fun getTicketsOffers(){
        choiseTicketsViewModel.getTicketsOffers()
        choiseTicketsViewModel.ticketsOffers.observe(viewLifecycleOwner, Observer { ticketsOffers ->
            Log.d("MyLog", "Полученные данные: $ticketsOffers")
            if (ticketsOffers.isNullOrEmpty()) {
                viewDataAdapter.items = listOf(
                    TicketOfferViewData(1, "Уральские пельмени",
                        listOf("нет сети", "это аварийные данные для демонстрации"), ValueViewData(2390)),
                    TicketOfferViewData(1, "Великая Победа",
                        listOf("почему вы моки отключили?", "сети все еще нет"), ValueViewData(2390)),
                    TicketOfferViewData(1, "NordPixar",
                        listOf("завтра в 13:00", "сегодня в 09:00"), ValueViewData(2390))
                )
                Log.d("MyLog", "Заглушка TicketOfferViewData показана")
            } else {
                viewDataAdapter.items = ticketsOffers
                Log.d("MyLog", "Данные обновлены в адаптере")
            }
            viewDataAdapter.notifyDataSetChanged()
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
        btnBack.setOnClickListener{
            findNavController().navigate(R.id.action_choiseTicketFragment_to_navigation_tickets)
        }
    }

    private fun backShowAllTickets(){
        btnSeeAll.setOnClickListener{
            findNavController().navigate(R.id.action_choiseTicketFragment_to_allTicketFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun errorObserve(){
        choiseTicketsViewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
            }
        })
    }

}