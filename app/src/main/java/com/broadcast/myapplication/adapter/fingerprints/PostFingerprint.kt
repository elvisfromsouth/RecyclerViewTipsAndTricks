package com.broadcast.myapplication.adapter.fingerprints

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.broadcast.myapplication.R
import com.broadcast.myapplication.adapter.BaseViewHolder
import com.broadcast.myapplication.adapter.Item
import com.broadcast.myapplication.adapter.ItemFingerprint
import com.broadcast.myapplication.databinding.ItemPostBinding
import com.broadcast.myapplication.model.UserPost

class PostFingerprint : ItemFingerprint<ItemPostBinding, UserPost> {

    override fun isRelativeItem(item: Item) = item is UserPost

    override fun getLayoutId() = R.layout.item_post

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemPostBinding, UserPost> {
        val binding = ItemPostBinding.inflate(layoutInflater, parent, false)
        return PostViewHolder(binding)
    }

}

class PostViewHolder(
    binding: ItemPostBinding
) : BaseViewHolder<ItemPostBinding, UserPost>(binding) {

    override fun onBind(item: UserPost) = with(binding) {
        tvCommentCount.text = item.commentsCount.toString()
        tvLikesCount.text = item.likesCount.toString()
        tvTitle.text = item.getPostDescription()
        ivPostImage.setImageDrawable(item.getPostDrawable())
    }


    private fun UserPost.getPostDrawable() = ContextCompat.getDrawable(binding.root.context, imageResId)

    private fun UserPost.getPostDescription() = SpannableStringBuilder("$userNickname $text").apply {
        setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            userNickname.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
    }

}
