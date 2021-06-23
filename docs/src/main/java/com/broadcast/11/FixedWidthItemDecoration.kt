package com.broadcast.`11`

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FixedWidthItemDecoration(
    private val width: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        view.layoutParams = view.layoutParams.also {
            it.width = width
        }
    }
}