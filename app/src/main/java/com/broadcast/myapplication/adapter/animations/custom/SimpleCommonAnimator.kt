package com.broadcast.myapplication.adapter.animations.custom

import androidx.recyclerview.widget.RecyclerView

class SimpleCommonAnimator : CommonItemAnimator {

    override fun animateRemove(holder: RecyclerView.ViewHolder) {
        holder.itemView.animate().alpha(0f)
    }

    override fun preAnimateRemove(holder: RecyclerView.ViewHolder) {
        holder.itemView.alpha = 1f
    }

    override fun animateAdd(holder: RecyclerView.ViewHolder) {
        holder.itemView.animate().alpha(1f)
    }

    override fun preAnimateAdd(holder: RecyclerView.ViewHolder) {
        holder.itemView.alpha = 0f
    }

}