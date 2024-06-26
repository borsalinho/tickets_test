package com.s21.presentation.ui.gelegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.s21.presentation.models.TicketOfferViewData
import com.s21.presentation.models.ViewData
import com.s21.ticketsapp.databinding.ItemTicketOfferBinding

class TicketOfferViewDataAdapterDelegate :
    AbsListItemAdapterDelegate<TicketOfferViewData, ViewData, TicketOfferViewDataAdapterDelegate.TicketOfferViewHolder>() {

    override fun isForViewType(item: ViewData, items: MutableList<ViewData>, position: Int) = item is TicketOfferViewData

    override fun onCreateViewHolder(parent: ViewGroup): TicketOfferViewHolder {
        val binding = ItemTicketOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TicketOfferViewHolder(binding)
    }

    override fun onBindViewHolder(item: TicketOfferViewData, holder: TicketOfferViewHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class TicketOfferViewHolder(private val binding: ItemTicketOfferBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TicketOfferViewData) {
            binding.title.text = data.title
        }
    }
}