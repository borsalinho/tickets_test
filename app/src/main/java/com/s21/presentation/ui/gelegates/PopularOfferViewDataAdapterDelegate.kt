package com.s21.presentation.ui.gelegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.s21.presentation.models.PopularOfferViewData
import com.s21.presentation.models.ViewData
import com.s21.ticketsapp.databinding.ItemPopularOfferBinding

class PopularOfferViewDataAdapterDelegate :
    AbsListItemAdapterDelegate<PopularOfferViewData, ViewData, PopularOfferViewDataAdapterDelegate.PopularOfferViewHolder>() {

    override fun isForViewType(item: ViewData, items: MutableList<ViewData>, position: Int) = item is PopularOfferViewData

    override fun onCreateViewHolder(parent: ViewGroup): PopularOfferViewHolder {
        val binding = ItemPopularOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularOfferViewHolder(binding)
    }

    override fun onBindViewHolder(item: PopularOfferViewData, holder: PopularOfferViewHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class PopularOfferViewHolder(private val binding: ItemPopularOfferBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PopularOfferViewData) {
            binding.title.text = data.title
            binding.status.text = data.status
        }
    }
}