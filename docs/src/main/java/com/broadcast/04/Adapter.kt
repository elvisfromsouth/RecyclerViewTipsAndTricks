package com.broadcast

import androidx.recyclerview.widget.RecyclerView


/**
 * @see RecyclerView.Adapter
 */
interface Adapter<VH : RecyclerView.ViewHolder> {


    /**
     * @see RecyclerView.Adapter.notifyDataSetChanged
     */
    fun notifyDataSetChanged()

    /**
     * @see RecyclerView.Adapter.notifyItemChanged(position)
     */
    fun notifyItemChanged(position: Int)

    /**
     * @see RecyclerView.Adapter.notifyItemInserted(position)
     */
    fun notifyItemInserted(position: Int)

    /**
     * @see RecyclerView.Adapter.notifyItemMoved(fromPosition, toPosition)
     */
    fun notifyItemMoved(fromPosition: Int, toPosition: Int)

    /**
     * @see RecyclerView.Adapter.notifyItemRemoved(position)
     */
    fun notifyItemRemoved(position: Int)

}