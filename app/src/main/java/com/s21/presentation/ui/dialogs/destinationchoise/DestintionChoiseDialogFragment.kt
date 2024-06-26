package com.s21.presentation.ui.dialogs.destinationchoise

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.s21.presentation.app.App
import com.s21.presentation.models.PopularOfferViewData

import com.s21.presentation.ui.adapters.ViewDataAdapter
import com.s21.presentation.ui.fragments.stubs.Stub1
import com.s21.presentation.ui.tickets.TicketsViewModel
import com.s21.ticketsapp.R
import com.s21.ticketsapp.databinding.DialogFragmentDestinationChoiseBinding
import javax.inject.Inject

class DestintionChoiseDialogFragment : DialogFragment() {

    private var _binding: DialogFragmentDestinationChoiseBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var ticketsViewModel: TicketsViewModel
    @Inject lateinit var destinationChoiseViewModel : DestinationChoiseViewModel
    @Inject lateinit var viewDataAdapter: ViewDataAdapter

    lateinit var btnToStub1 : ImageButton
    lateinit var btnToStub2 : ImageButton
    lateinit var btnToStub3 : ImageButton
    lateinit var btnToStub4 : ImageButton

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
        val destinationPoint = binding.editDestinationPoint
        btnToStub1 = binding.imageButton
        btnToStub2 = binding.imageButton2
        btnToStub3 = binding.imageButton3
        btnToStub4 = binding.imageButton4

        setDeparturePointValue(departurePoint)

        saveOnSharedPreferences(departurePoint)

        viewDataAdapter.setOnItemClickListener { item ->
            val destination = (item as? PopularOfferViewData)?.title ?: ""
            ticketsViewModel.setDestinationPoint(destination)
            openChoiseTickets()
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewDataAdapter
        }

        getPopularOffers()

        setDestinationPointValue(destinationPoint)

        errorObserve()
        listenerForButtons()

        ticketsViewModel.destinationPoint.observe(viewLifecycleOwner, Observer { destination ->
            Log.d("MyTag", "Observed destinationPoint: $destination")
        })

        binding.button.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun listenerForButtons(){
        btnToStub1.setOnClickListener {
            dismiss()
            parentFragment
                ?.findNavController()
                ?.navigate(R.id.action_destinationChoise_to_stub1)
        }
        btnToStub2.setOnClickListener {
            dismiss()
            parentFragment
                ?.findNavController()
                ?.navigate(R.id.action_destinationChoise_to_stub1)
        }
        btnToStub3.setOnClickListener {
            dismiss()
            parentFragment
                ?.findNavController()
                ?.navigate(R.id.action_destinationChoise_to_stub1)
        }
        btnToStub4.setOnClickListener {
            dismiss()
            parentFragment
                ?.findNavController()
                ?.navigate(R.id.action_destinationChoise_to_stub1)
        }
    }

    private fun setDeparturePointValue(departurePoint : EditText){
        ticketsViewModel.departurePoint.observe(viewLifecycleOwner, Observer {
            if (departurePoint.text.toString() != it) {
                departurePoint.setText(it)
                departurePoint.setSelection(it.length)
            }
        })

    }

    private fun setDestinationPointValue(destinationPoint: EditText){
        destinationPoint.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                val text = destinationPoint.text.toString()
                ticketsViewModel.setDestinationPoint(text)
                openChoiseTickets()
                true
            } else {
                false
            }
        }
    }

    private fun errorObserve(){
        destinationChoiseViewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getPopularOffers(){
        destinationChoiseViewModel.getPopularOffers()
        destinationChoiseViewModel.popularOffers.observe(viewLifecycleOwner, Observer { offers ->
            viewDataAdapter.items = offers
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

    private fun openChoiseTickets() {
        dismiss()
        parentFragment
            ?.findNavController()
            ?.navigate(R.id.action_destinationChoise_to_choiseTicket)
    }


}