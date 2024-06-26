package com.s21.presentation.ui.gelegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.s21.presentation.models.OfferViewData
import com.s21.presentation.models.ViewData
import com.s21.ticketsapp.R
import com.s21.ticketsapp.databinding.ItemOfferBinding

class OfferViewDataAdapterDelegate :
    AbsListItemAdapterDelegate<OfferViewData, ViewData, OfferViewDataAdapterDelegate.OfferViewHolder>() {

    override fun isForViewType(item: ViewData, items: MutableList<ViewData>, position: Int) = item is OfferViewData

    override fun onCreateViewHolder(parent: ViewGroup): OfferViewHolder {
        val binding = ItemOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OfferViewHolder(binding)
    }

    override fun onBindViewHolder(item: OfferViewData, holder: OfferViewHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class OfferViewHolder(private val binding: ItemOfferBinding) : RecyclerView.ViewHolder(binding.root) {

        private val images = listOf(
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
        )

        fun bind(data: OfferViewData) {
            binding.title.text = data.title
            val imageRes = images.getOrNull(data.id - 1)
            if (imageRes != null) {
                binding.image.setImageResource(imageRes) // ну мало ли, картинки куда-то пропадут
            }

            binding.price.text = data.price.value.toString()
            binding.town.text = data.town
        }
    }
}