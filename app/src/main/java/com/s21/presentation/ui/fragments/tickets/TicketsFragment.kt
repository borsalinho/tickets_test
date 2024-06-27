package com.s21.presentation.ui.tickets

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.s21.presentation.app.App
import com.s21.presentation.models.OfferViewData
import com.s21.presentation.models.ValueViewData
import com.s21.presentation.ui.adapters.ViewDataAdapter
import com.s21.presentation.ui.dialogs.destinationchoise.DestintionChoiseDialogFragment

import com.s21.ticketsapp.databinding.FragmentTicketsBinding
import javax.inject.Inject

class TicketsFragment : Fragment() {

    private var _binding: FragmentTicketsBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var ticketsViewModel : TicketsViewModel
    @Inject lateinit var viewDataAdapter: ViewDataAdapter

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
        val destinationPoint = binding.editDestinationPoint

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = viewDataAdapter
        }


        setDeparturePointValue(departurePoint)
        saveOnSharedPreferences(departurePoint)
        getOffers()

        destinationPoint.setOnClickListener {
            val dialogFragment = DestintionChoiseDialogFragment()
            dialogFragment.show(childFragmentManager, "DestintionChoiseDialogFragment")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getOffers(){
        ticketsViewModel.getOffers()
        ticketsViewModel.offers.observe(viewLifecycleOwner, Observer { offers ->
            if (offers.isNullOrEmpty()) {
                viewDataAdapter.items = listOf(
                    OfferViewData(1, "Я заглушка 1", "тк Нет сети", ValueViewData(500)),
                    OfferViewData(2, "Я заглушка 2", "Нет сети же", ValueViewData(500)),
                    OfferViewData(3, "Я заглушка 3", "все еще Нет сети", ValueViewData(500)),
                )
                Log.d("MyLog", "Заглушка данных показана")
            } else {
                viewDataAdapter.items = offers
                Log.d("MyLog", "Данные обновлены в адаптере")
            }
            viewDataAdapter.notifyDataSetChanged()
        })


    }

    private fun setDeparturePointValue(departurePoint : EditText){
        ticketsViewModel.departurePoint.observe(viewLifecycleOwner, Observer {
            if (departurePoint.text.toString() != it) {
                departurePoint.setText(it)
                departurePoint.setSelection(it.length)
            }
        })
    }

    private fun saveOnSharedPreferences(departurePoint : EditText){
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