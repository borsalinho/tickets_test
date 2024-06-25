package com.s21.presentation.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.s21.presentation.models.ViewData

class ViewDataAdapter (
    delegates: List<AdapterDelegate<List<ViewData>>>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val delegatesManager = AdapterDelegatesManager<List<ViewData>>()
    private var clickListener: ((ViewData) -> Unit)? = null

    init {
        delegates.forEach { delegate ->
            delegatesManager.addDelegate(delegate)
        }
    }

    var items: List<ViewData> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = delegatesManager.onCreateViewHolder(parent, viewType)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                clickListener?.invoke(items[position])
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(items, position, holder)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(items, position)
    }

    fun setOnItemClickListener(listener: (ViewData) -> Unit) {
        clickListener = listener
    }
}