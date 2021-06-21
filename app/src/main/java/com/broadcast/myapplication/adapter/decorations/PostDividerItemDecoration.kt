package com.broadcast.myapplication.adapter.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GroupVerticalItemDecoration(
    private val viewType: Int,
    private val innerDivider: Int,
    private val outerDivider: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildViewHolder(view).itemViewType != viewType) return

        val adapter = parent.adapter ?: return
        val currentPosition = parent.getChildAdapterPosition(view).takeIf { it != RecyclerView.NO_POSITION } ?: return

        val isPrevTargetView = adapter.isPrevTargetView(currentPosition, viewType)
        val isNextTargetView = adapter.isNextTargetView(currentPosition, viewType)

        val oneSideInnerDivider = innerDivider / 2

        with(outRect) {
            top = if (isPrevTargetView) oneSideInnerDivider else outerDivider
            bottom = if (isNextTargetView) oneSideInnerDivider else outerDivider
        }
    }

    private fun RecyclerView.Adapter<*>.isPrevTargetView(
        currentPosition: Int,
        viewType: Int
    ) = currentPosition != 0 && getItemViewType(currentPosition - 1) == viewType

    private fun RecyclerView.Adapter<*>.isNextTargetView(
        currentPosition: Int,
        viewType: Int
    ): Boolean {
        val lastIndex = itemCount - 1
        return currentPosition != lastIndex && getItemViewType(currentPosition + 1) == viewType
    }

}