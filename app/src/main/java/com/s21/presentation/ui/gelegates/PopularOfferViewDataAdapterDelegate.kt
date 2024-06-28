package com.s21.presentation.ui.gelegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.s21.presentation.models.PopularOfferViewData
import com.s21.presentation.models.ViewData
import com.s21.ticketsapp.R
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

        val images = listOf(
            R.drawable.image_4,
            R.drawable.image_5,
            R.drawable.image_6,
        )

        fun bind(data: PopularOfferViewData) {
            val imageRes = images.getOrNull(adapterPosition % images.size)
            if (imageRes != null) {
                binding.imageView3.setImageResource(imageRes) // ну мало ли, картинки куда-то пропадут
            } else (
                    binding.imageView3.setImageResource(R.drawable.image_6)
            )

            binding.title.text = data.title
            binding.status.text = data.status
        }
    }
}