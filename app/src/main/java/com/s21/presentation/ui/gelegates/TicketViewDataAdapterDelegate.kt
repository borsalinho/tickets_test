package com.s21.presentation.ui.gelegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.s21.presentation.models.TicketViewData
import com.s21.presentation.models.ViewData
import com.s21.ticketsapp.databinding.ItemTicketBinding

class TicketViewDataAdapterDelegate :
    AbsListItemAdapterDelegate<TicketViewData, ViewData, TicketViewDataAdapterDelegate.TicketViewHolder>() {

    override fun isForViewType(item: ViewData, items: MutableList<ViewData>, position: Int) = item is TicketViewData

    override fun onCreateViewHolder(parent: ViewGroup): TicketViewHolder {
        val binding = ItemTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TicketViewHolder(binding)
    }

    override fun onBindViewHolder(item: TicketViewData, holder: TicketViewHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class TicketViewHolder(private val binding: ItemTicketBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TicketViewData) {
            binding.title.text = data.id.toString()
        }
    }
}