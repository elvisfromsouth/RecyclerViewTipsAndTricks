package com.broadcast.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.broadcast.myapplication.adapter.FingerprintAdapter
import com.broadcast.myapplication.adapter.Item
import com.broadcast.myapplication.adapter.animations.AddableItemAnimator
import com.broadcast.myapplication.adapter.animations.custom.SimpleCommonAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInLeftCommonAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInTopCommonAnimator
import com.broadcast.myapplication.adapter.decorations.FeedHorizontalDividerItemDecoration
import com.broadcast.myapplication.adapter.decorations.GroupVerticalItemDecoration
import com.broadcast.myapplication.adapter.fingerprints.PostFingerprint
import com.broadcast.myapplication.adapter.fingerprints.TitleFingerprint
import com.broadcast.myapplication.databinding.ActivityMainBinding
import com.broadcast.myapplication.model.FeedTitle
import com.broadcast.myapplication.model.UserPost
import com.broadcast.myapplication.utils.SwipeToDelete
import com.broadcast.myapplication.utils.getRandomFeed
import com.broadcast.myapplication.utils.getRandomUserPost
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FingerprintAdapter
    private val feed: MutableList<Item> by lazy(LazyThreadSafetyMode.NONE) { getRandomFeed(this) }

    private val titlesList: MutableList<Item> by lazy {
        MutableList(1) { FeedTitle("Актуальное за сегодня:") }
    }
    private val postsList: MutableList<Item> by lazy {
        MutableList(10) { getRandomUserPost(this) }
    }

    private val titleAdapter = FingerprintAdapter(listOf(TitleFingerprint()))
    private val postAdapter = FingerprintAdapter(listOf(PostFingerprint(::onSavePost)))

    private val concatAdapter = ConcatAdapter(
        ConcatAdapter.Config.Builder()
            .setIsolateViewTypes(false)
            .build(),
        titleAdapter,
        postAdapter
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        adapter = FingerprintAdapter(getFingerprints())

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = concatAdapter

            addItemDecoration(FeedHorizontalDividerItemDecoration(70)) // addable
            addItemDecoration(GroupVerticalItemDecoration(R.layout.item_post, 100, 0)) // addable
            addItemDecoration(GroupVerticalItemDecoration(R.layout.item_title, 0, 100)) // addable

            itemAnimator = AddableItemAnimator(SimpleCommonAnimator()).also { animator ->
                animator.addViewTypeAnimation(R.layout.item_post, SlideInLeftCommonAnimator())
                animator.addViewTypeAnimation(R.layout.item_title, SlideInTopCommonAnimator())
                animator.addDuration = 500L
                animator.removeDuration = 500L
            }
        }

        initSwipeToDelete()
        submitInitialListWithDelayForAnimation()
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

    private fun submitInitialListWithDelayForAnimation() {
        binding.recyclerView.postDelayed({
            titleAdapter.submitList(titlesList.toList())
            postAdapter.submitList(postsList.toList())
        }, 300L)
    }

    private fun initSwipeToDelete() {
        val onItemSwipedToDelete = { positionForRemove: Int ->
            val removedItem = feed[positionForRemove]
            feed.removeAt(positionForRemove)
            adapter.submitList(feed.toList())

            showRestoreItemSnackbar(positionForRemove, removedItem)

        }
        val swipeToDeleteCallback = SwipeToDelete(onItemSwipedToDelete)
        ItemTouchHelper(swipeToDeleteCallback).attachToRecyclerView(binding.recyclerView)
    }

    private fun showRestoreItemSnackbar(position: Int, item: Item) {
        Snackbar.make(binding.recyclerView, "Item was deleted", Snackbar.LENGTH_LONG)
            .setAction("Undo") {
                feed.add(position, item)
                adapter.submitList(feed.toList())
            }.show()
    }
}