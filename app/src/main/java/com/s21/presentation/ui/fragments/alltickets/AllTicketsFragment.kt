package com.s21.presentation.ui.fragments.alltickets

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.s21.domain.models.Ticket
import com.s21.presentation.app.App
import com.s21.presentation.models.ArrivalViewData
import com.s21.presentation.models.DepartureViewData
import com.s21.presentation.models.HandLuggageViewData
import com.s21.presentation.models.TicketViewData
import com.s21.presentation.models.ValueViewData
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

    private lateinit var btnback : ImageButton
    private lateinit var textDepartDest : TextView
    private lateinit var textPassanger : TextView

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
        textPassanger = binding.textPassanger

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewDataAdapter
        }

        savePoints()
        getTickets()

        setPassengerObservers()

        backOnChoiseTicketsFragment()
        errorObserve()
        return root
    }

    private fun setPassengerObservers() {
        choiseTicketsViewModel.departureDate.observe(viewLifecycleOwner, Observer {
            textPassanger.text = it + ", 1 пассажир"
        })

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
        _binding = null
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
        allTicketsViewModel.tickets.observe(viewLifecycleOwner, Observer { tickets ->
            Log.d("MyLog", "Полученные данные: $tickets")
            if (tickets.isNullOrEmpty()) {
                viewDataAdapter.items = listOf(
                    TicketViewData(1, "Моки не работают", ValueViewData(10360), "qwertt",
                        "qweqwe",
                        DepartureViewData("Мочи", "13:10", "CWW"),
                        ArrivalViewData("Сочква", "19:10", "GHJ"),
                        true, false,null,
                        HandLuggageViewData(true, null),
                        true, true
                    ),
                    TicketViewData(2, "Без моков не могу посчитать корректно время", ValueViewData(3225), "qwertt",
                        "qweqwe",
                        DepartureViewData("Казань", "00:10", "ККК"),
                        ArrivalViewData("Ньячанг", "18:10", "РРР"),
                        false, false,null,
                        HandLuggageViewData(true, null),
                        true, true
                    ),
                    TicketViewData(3, null, ValueViewData(32250), "qwertt",
                        "qweqwe",
                        DepartureViewData("Плотва", "10:10", "rrW"),
                        ArrivalViewData("Геральд", "18:10", "Gkk"),
                        false, false,null,
                        HandLuggageViewData(true, null),
                        true, true
                    ),
                    TicketViewData(4, null, ValueViewData(1025), "qwertt",
                        "qweqwe",
                        DepartureViewData("Покемон", "10:10", "rrW"),
                        ArrivalViewData("Чита", "18:10", "Gkk"),
                        false, false,null,
                        HandLuggageViewData(true, null),
                        true, true
                    )
                )
                Log.d("MyLog", "Заглушка TicketOfferViewData показана")
            } else {
                viewDataAdapter.items = tickets
                Log.d("MyLog", "Данные обновлены в адаптере")
            }
            viewDataAdapter.notifyDataSetChanged()
        })
    }

}

