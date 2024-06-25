package com.s21.presentation.ui.gelegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.s21.presentation.models.PopularOfferViewData
import com.s21.presentation.models.ViewData
import com.s21.ticketsapp.R

class PopularOfferViewDataAdapterDelegate :
    AbsListItemAdapterDelegate<PopularOfferViewData, ViewData, PopularOfferViewDataAdapterDelegate.PopularOfferViewHolder>() {

        override fun isForViewType(item: ViewData, items: MutableList<ViewData>, position: Int) = item is PopularOfferViewData

        override fun onCreateViewHolder(parent: ViewGroup): PopularOfferViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular_offer, parent, false)
            return PopularOfferViewHolder(view)
        }

        override fun onBindViewHolder(item: PopularOfferViewData, holder: PopularOfferViewHolder, payloads: MutableList<Any>) {
            holder.bind(item)
        }

        class PopularOfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val title: TextView = itemView.findViewById(R.id.title)
            private val status: TextView = itemView.findViewById(R.id.status)
            fun bind(data: PopularOfferViewData) {
                title.text = data.title
                status.text = data.status
            }
        }
}