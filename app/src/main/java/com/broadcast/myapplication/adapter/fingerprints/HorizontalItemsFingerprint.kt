package com.broadcast.myapplication.adapter.fingerprints

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.broadcast.myapplication.R
import com.broadcast.myapplication.adapter.BaseViewHolder
import com.broadcast.myapplication.adapter.FingerprintAdapter
import com.broadcast.myapplication.adapter.Item
import com.broadcast.myapplication.adapter.ItemFingerprint
import com.broadcast.myapplication.adapter.decorations.HorizontalDividerDecoration
import com.broadcast.myapplication.databinding.ItemHorizontalListBinding
import com.broadcast.myapplication.model.HorizontalItems

class HorizontalItemsFingerprint(
    private val fingerprintsList: List<ItemFingerprint<*, *>>,
    private val outerDivider: Int,
) : ItemFingerprint<ItemHorizontalListBinding, HorizontalItems> {

    override fun isRelativeItem(item: Item) = item is HorizontalItems

    override fun getLayoutId() = R.layout.item_horizontal_list

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemHorizontalListBinding, HorizontalItems> {
        val binding = ItemHorizontalListBinding.inflate(layoutInflater)
        return HorizontalItemsHolder(binding, fingerprintsList, outerDivider)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<HorizontalItems>() {

        override fun areItemsTheSame(oldItem: HorizontalItems, newItem: HorizontalItems) = oldItem == newItem

        override fun areContentsTheSame(oldItem: HorizontalItems, newItem: HorizontalItems) = oldItem == newItem

    }

}

class HorizontalItemsHolder(
    binding: ItemHorizontalListBinding,
    fingerprints: List<ItemFingerprint<*, *>>,
    outerDivider: Int,
) : BaseViewHolder<ItemHorizontalListBinding, HorizontalItems>(binding) {

    private val fingerprintAdapter = FingerprintAdapter(fingerprints)

    init {
        with(binding.rvHorizontalItems) {
            adapter = fingerprintAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            addItemDecoration(HorizontalDividerDecoration(50, outerDivider))
        }
    }

    override fun onBind(item: HorizontalItems) {
        super.onBind(item)
        fingerprintAdapter.submitList(item.items)
        binding.rvHorizontalItems.restoreState(item.state)
    }

    override fun onViewDetached() {
        item.state = binding.rvHorizontalItems.layoutManager?.onSaveInstanceState() ?: return
    }

    private fun RecyclerView.restoreState(parcelable: Parcelable?) {
        if (parcelable == null) return
        layoutManager?.onRestoreInstanceState(parcelable)
    }

}