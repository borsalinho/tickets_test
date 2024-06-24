package com.s21.presentation.ui.dialogs.destinationchoise

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.s21.presentation.app.App
import com.s21.presentation.ui.tickets.TicketsViewModel
import com.s21.ticketsapp.databinding.DialogFragmentDestinationChoiseBinding
import javax.inject.Inject

class DestintionChoiseDialogFragment : DialogFragment() {

    private var _binding: DialogFragmentDestinationChoiseBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var ticketsViewModel: TicketsViewModel
    @Inject lateinit var destinationChoiseViewModel : DestinationChoiseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogFragmentDestinationChoiseBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val departurePoint = binding.editDeparturePoint

        setDeparturePointValue(departurePoint)

        saveOnSharedPreferences(departurePoint)

        getTicketOfferViewData()


        binding.button.setOnClickListener {
            dismiss()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setDeparturePointValue(departurePoint : EditText){
        ticketsViewModel.departurePoint.observe(viewLifecycleOwner, Observer {
            if (departurePoint.text.toString() != it) {
                departurePoint.setText(it)
                departurePoint.setSelection(it.length)
            }
        })
    }

    private fun getTicketOfferViewData(){
        try {
            destinationChoiseViewModel.getTicketOfferViewData()

            destinationChoiseViewModel.ticketsOffers.observe(viewLifecycleOwner, Observer { tickets ->
                Log.d("MyLog", "i am a getTicketOfferViewData")
                Log.d("MyLog", tickets.toString())
            })
        } catch (e:Exception){
            Toast.makeText(requireActivity(),"Не удалось загрузить из сети", Toast.LENGTH_LONG).show()
        }
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