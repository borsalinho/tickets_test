package com.s21.presentation.ui.gelegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.s21.presentation.models.TicketOfferViewData
import com.s21.presentation.models.ViewData
import com.s21.ticketsapp.R

class TicketOfferViewDataAdapterDelegate :
    AbsListItemAdapterDelegate<TicketOfferViewData, ViewData, TicketOfferViewDataAdapterDelegate.TicketOfferViewHolder>() {

    override fun isForViewType(item: ViewData, items: MutableList<ViewData>, position: Int) = item is TicketOfferViewData

    override fun onCreateViewHolder(parent: ViewGroup): TicketOfferViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket_offer, parent, false)
        return TicketOfferViewHolder(view)
    }

    override fun onBindViewHolder(item: TicketOfferViewData, holder: TicketOfferViewHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class TicketOfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.title)

        fun bind(data: TicketOfferViewData) {
            titleTextView.text = data.title
        }
    }
}