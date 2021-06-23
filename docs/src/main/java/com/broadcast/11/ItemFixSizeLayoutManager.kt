package com.broadcast.`11`

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemFixSizeLayoutManager : LinearLayoutManager {
    constructor(context: Context?) : super(context)

    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout
    )

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )


    override fun generateDefaultLayoutParams() =
        scaledLayoutParams(super.generateDefaultLayoutParams())

    override fun generateLayoutParams(
        lp: ViewGroup.LayoutParams?
    ) = scaledLayoutParams(super.generateLayoutParams(lp))

    override fun generateLayoutParams(
        c: Context?,
        attrs: AttributeSet?
    ) =
        scaledLayoutParams(super.generateLayoutParams(c, attrs))

    private fun scaledLayoutParams(
        layoutParams: RecyclerView.LayoutParams
    ) = layoutParams.also {
        it.width = 350
    }

}