package com.broadcast

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.broadcast.helpers.CardItem
import com.broadcast.helpers.CardItemViewHolder
import com.broadcast.helpers.TitleViewHolder

class FirstRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<Any> = listOf()

    private companion object {
        val CARD_VIEWTYPE = 1
        val TITLE_VIEWTYPE = 2
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = when (viewType) {
        CARD_VIEWTYPE -> CardItemViewHolder(View(parent.context))
        TITLE_VIEWTYPE -> TitleViewHolder(View(parent.context))
        else -> throw IllegalArgumentException("Illegal type: $viewType")
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is CardItemViewHolder -> holder.onBind(items[position])
            is TitleViewHolder -> holder.onBind(items[position])
        }
    }

    override fun getItemViewType(
        position: Int
    ) = when (items[position]) {
        is CardItem -> CARD_VIEWTYPE
        is String -> TITLE_VIEWTYPE
        else -> throw IllegalArgumentException("Illegal type: $position")
    }

    override fun getItemCount() = items.size

}