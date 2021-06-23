package com.broadcast

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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

class SecondRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<Any> = listOf()

    private companion object {
        val FIRST_NAME_VIEWTYPE = 1
        val SECOND_NAME_VIEWTYPE = 2
        val LAST_ORDER_VIEWTYPE = 3
        val SUBSCRIBERS_VIEWTYPE = 4
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = when (viewType) {
        FIRST_NAME_VIEWTYPE -> FirstNameViewHolder(View(parent.context))
        SECOND_NAME_VIEWTYPE -> SecondNameViewHolder(View(parent.context))
        LAST_ORDER_VIEWTYPE -> LastOrderViewHolder(View(parent.context))
        SUBSCRIBERS_VIEWTYPE -> SubscribersViewHolder(View(parent.context))
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
        is FirstNameItem -> FIRST_NAME_VIEWTYPE
        is SecondNameItem -> SECOND_NAME_VIEWTYPE
        is LastOrderItem -> LAST_ORDER_VIEWTYPE
        is SubscribersItem -> SUBSCRIBERS_VIEWTYPE
        else -> throw IllegalArgumentException("Illegal type: $position")
    }

    override fun getItemCount() = items.size

}