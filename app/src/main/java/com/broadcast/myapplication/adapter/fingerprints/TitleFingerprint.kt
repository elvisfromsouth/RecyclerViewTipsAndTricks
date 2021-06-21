package com.broadcast.myapplication.adapter.fingerprints

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.broadcast.myapplication.R
import com.broadcast.myapplication.adapter.BaseViewHolder
import com.broadcast.myapplication.adapter.Item
import com.broadcast.myapplication.adapter.ItemFingerprint
import com.broadcast.myapplication.databinding.ItemTitleBinding
import com.broadcast.myapplication.model.FeedTitle

class TitleFingerprint : ItemFingerprint<ItemTitleBinding, FeedTitle> {

    override fun isRelativeItem(item: Item) = item is FeedTitle

    override fun getLayoutId() = R.layout.item_title

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemTitleBinding, FeedTitle> {
        val binding = ItemTitleBinding.inflate(layoutInflater, parent, false)
        return TitleViewHolder(binding)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<FeedTitle>() {
        override fun areItemsTheSame(oldItem: FeedTitle, newItem: FeedTitle) = oldItem.title == oldItem.title

        override fun areContentsTheSame(oldItem: FeedTitle, newItem: FeedTitle) = oldItem == oldItem
    }

}

class TitleViewHolder(
    binding: ItemTitleBinding
) : BaseViewHolder<ItemTitleBinding, FeedTitle>(binding) {

    override fun onBind(item: FeedTitle) {
        super.onBind(item)
        binding.tvFeedTitle.text = item.title
    }

}
