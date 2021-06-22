package com.broadcast.myapplication.adapter.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FeedHorizontalDividerItemDecoration(
    private val divider: Int,
    private val excludeViewTypes: List<Int>
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val viewType = parent.getChildViewHolder(view).itemViewType
        if (excludeViewTypes.contains(viewType)) return

        val oneSideHorizontalDivider = divider / 2

        with(outRect) {
            left = oneSideHorizontalDivider
            right = oneSideHorizontalDivider
        }
    }

}