package com.s21.presentation.ui.gelegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.s21.presentation.models.TicketViewData
import com.s21.presentation.models.ViewData
import com.s21.ticketsapp.R
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


            if (data.badge.isNullOrEmpty()) {
                binding.badge.visibility = View.GONE
            } else {
                binding.badge.text = data.badge
            }
            binding.price.text = data.price.value.toString() + " ₽"
            binding.textDepartTime.text = data.departure.date // я не помню какой текст приходил, поэтому пусть будет так
            binding.textArriveTime.text = data.arrival.date
            binding.textDepartAir.text = data.departure.airport
            binding.textArriveTime.text = data.arrival.airport
            binding.imgBall.setImageResource(R.drawable.circle_red)
            if (data.has_transfer) {
                binding.textIsTransfer.text = "/ Без пересадок"
            } else {
                binding.textIsTransfer.text = ""
            }
        }
    }
}