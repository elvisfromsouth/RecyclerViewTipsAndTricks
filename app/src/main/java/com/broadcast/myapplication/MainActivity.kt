package com.broadcast.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.broadcast.myapplication.adapter.FingerprintAdapter
import com.broadcast.myapplication.adapter.Item
import com.broadcast.myapplication.adapter.decorations.FeedHorizontalDividerItemDecoration
import com.broadcast.myapplication.adapter.decorations.GroupVerticalItemDecoration
import com.broadcast.myapplication.adapter.fingerprints.PostFingerprint
import com.broadcast.myapplication.adapter.fingerprints.TitleFingerprint
import com.broadcast.myapplication.databinding.ActivityMainBinding
import com.broadcast.myapplication.model.UserPost
import com.broadcast.myapplication.utils.getRandomFeed

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FingerprintAdapter
    private val feed: MutableList<Item> by lazy(LazyThreadSafetyMode.NONE) { getRandomFeed(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        adapter = FingerprintAdapter(getFingerprints())

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter

            addItemDecoration(FeedHorizontalDividerItemDecoration(70)) // addable
            addItemDecoration(GroupVerticalItemDecoration(R.layout.item_post, 100, 0)) // addable
            addItemDecoration(GroupVerticalItemDecoration(R.layout.item_title, 0, 100)) // addable
        }

        adapter.submitList(feed.toList())
    }

    private fun getFingerprints() = listOf(
        TitleFingerprint(),
        PostFingerprint(::onSavePost)
    )

    private fun onSavePost(post: UserPost) {
        val postIndex = feed.indexOf(post)
        val newItem = post.copy(isSaved = post.isSaved.not())

        feed.removeAt(postIndex)
        feed.add(postIndex, newItem)
        adapter.submitList(feed.toList())
    }
}