package com.broadcast.myapplication.adapter.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalDividerDecoration(
    private val divider: Int,
    private val outerDivider: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val viewHolder = parent.getChildViewHolder(view)
        val itemCount = parent.adapter?.itemCount ?: return
        val adapterLastIndex = itemCount - 1
        val itemPosition = viewHolder.absoluteAdapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: viewHolder.oldPosition

        val oneSizeDivider = divider / 2
        val oneSizeOuterDivider = outerDivider / 2

        outRect.left = if (itemPosition == 0) oneSizeOuterDivider else oneSizeDivider
        outRect.right = if (itemPosition == adapterLastIndex) oneSizeOuterDivider else oneSizeDivider
    }

}