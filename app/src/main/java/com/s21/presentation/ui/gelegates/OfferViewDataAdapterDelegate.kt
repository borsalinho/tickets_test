package com.s21.presentation.ui.gelegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.s21.presentation.models.OfferViewData
import com.s21.presentation.models.ViewData
import com.s21.ticketsapp.R

class OfferViewDataAdapterDelegate :
    AbsListItemAdapterDelegate<OfferViewData, ViewData, OfferViewDataAdapterDelegate.OfferViewHolder>() {

    override fun isForViewType(item: ViewData, items: MutableList<ViewData>, position: Int) = item is OfferViewData

    override fun onCreateViewHolder(parent: ViewGroup): OfferViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular_offer, parent, false)
        return OfferViewHolder(view)
    }

    override fun onBindViewHolder(item: OfferViewData, holder: OfferViewHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)
        fun bind(data: OfferViewData) {
            title.text = data.title
        }
    }
}