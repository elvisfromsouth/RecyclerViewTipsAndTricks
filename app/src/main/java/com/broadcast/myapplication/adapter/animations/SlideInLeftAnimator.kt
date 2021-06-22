package com.broadcast.myapplication.adapter.animations

import android.view.animation.Interpolator
import androidx.recyclerview.widget.RecyclerView

class SlideInLeftAnimator : BaseItemAnimator {
    constructor()
    constructor(interpolator: Interpolator) {
        this.interpolator = interpolator
    }

    override fun animateRemoveImpl(holder: RecyclerView.ViewHolder) {
        holder.itemView.animate().apply {
            translationX(-holder.itemView.rootView.width.toFloat())
            duration = removeDuration
            interpolator = this@SlideInLeftAnimator.interpolator
            setListener(DefaultRemoveAnimatorListener(holder))
            startDelay = getRemoveDelay(holder)
        }.start()
    }

    override fun preAnimateAddImpl(holder: RecyclerView.ViewHolder) {
        holder.itemView.translationX = -holder.itemView.rootView.width.toFloat()
    }

    override fun animateAddImpl(holder: RecyclerView.ViewHolder) {
        holder.itemView.animate().apply {
            translationX(0f)
            duration = addDuration
            interpolator = this@SlideInLeftAnimator.interpolator
            setListener(DefaultAddAnimatorListener(holder))
            startDelay = getAddDelay(holder)
        }.start()
    }
}