package com.s21.presentation.ui.fragments.stubs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.s21.ticketsapp.R

class Stub1 : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.stub, container, false)

        val buttonBack = view.findViewById<Button>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }
}