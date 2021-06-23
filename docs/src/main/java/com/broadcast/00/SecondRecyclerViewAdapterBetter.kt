package com.broadcast

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.broadcast.docs.R
import com.broadcast.helpers.CardItem
import com.broadcast.helpers.CardItemViewHolder
import com.broadcast.helpers.FirstNameItem
import com.broadcast.helpers.FirstNameViewHolder
import com.broadcast.helpers.LastOrderItem
import com.broadcast.helpers.LastOrderViewHolder
import com.broadcast.helpers.SecondNameItem
import com.broadcast.helpers.SecondNameViewHolder
import com.broadcast.helpers.SubscribersItem
import com.broadcast.helpers.SubscribersViewHolder
import com.broadcast.helpers.TitleViewHolder

class SecondRecyclerViewAdapterBetter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<Any> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = when (viewType) {
        R.layout.item_firstname -> FirstNameViewHolder(View(parent.context))
        R.layout.item_secondname -> SecondNameViewHolder(View(parent.context))
        R.layout.item_lastorder -> LastOrderViewHolder(View(parent.context))
        R.layout.item_subscribers  -> SubscribersViewHolder(View(parent.context))
        else -> throw IllegalArgumentException("Illegal type: $viewType")
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is FirstNameViewHolder -> holder.onBind(items[position])
            is SecondNameViewHolder -> holder.onBind(items[position])
            is LastOrderViewHolder -> holder.onBind(items[position])
            is SubscribersViewHolder -> holder.onBind(items[position])
        }
    }

    override fun getItemViewType(
        position: Int
    ) = when (items[position]) {
        is FirstNameItem -> R.layout.item_firstname
        is SecondNameItem -> R.layout.item_secondname
        is LastOrderItem -> R.layout.item_lastorder
        is SubscribersItem -> R.layout.item_subscribers
        else -> throw IllegalArgumentException("Illegal type: $position")
    }

    override fun getItemCount() = items.size

}