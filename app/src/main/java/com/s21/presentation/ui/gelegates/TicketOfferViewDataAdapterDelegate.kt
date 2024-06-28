package com.s21.presentation.ui.gelegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.s21.presentation.models.PopularOfferViewData
import com.s21.presentation.models.TicketOfferViewData
import com.s21.presentation.models.ValueViewData
import com.s21.presentation.models.ViewData
import com.s21.ticketsapp.R
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

        val images = listOf(
            R.drawable.circle_red,
            R.drawable.circle_blue,
            R.drawable.circle_white,
        )

        fun bind(data: TicketOfferViewData) {
            val imageRes = images.getOrNull(adapterPosition % images.size)
            if (imageRes != null) {
                binding.imageView5.setImageResource(imageRes) // ну мало ли, картинки куда-то пропадут
            }
            binding.titleTO.text = data.title
            binding.priceTO.text = "${data.price.value} ₽ >"

            binding.timesTO.text = data.time_range.joinToString(", ")

        }
    }
}

