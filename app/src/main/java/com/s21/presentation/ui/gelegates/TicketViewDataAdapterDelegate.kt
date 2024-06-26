package com.s21.presentation.ui.gelegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

import com.s21.presentation.models.TicketViewData
import com.s21.presentation.models.ViewData
import com.s21.ticketsapp.R

class TicketViewDataAdapterDelegate :
    AbsListItemAdapterDelegate<TicketViewData, ViewData, TicketViewDataAdapterDelegate.TicketViewHolder>() {

    override fun isForViewType(item: ViewData, items: MutableList<ViewData>, position: Int) = item is TicketViewData

    override fun onCreateViewHolder(parent: ViewGroup): TicketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket, parent, false)
        return TicketViewHolder(view)
    }

    override fun onBindViewHolder(item: TicketViewData, holder: TicketViewHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class TicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.title)

        fun bind(data: TicketViewData) {
            titleTextView.text = data.id.toString()
        }
    }
}