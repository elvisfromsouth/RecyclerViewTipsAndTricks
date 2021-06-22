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
import com.broadcast.myapplication.adapter.fingerprints.HorizontalItemsFingerprint
import com.broadcast.myapplication.adapter.fingerprints.PostFingerprint
import com.broadcast.myapplication.adapter.fingerprints.TitleFingerprint
import com.broadcast.myapplication.databinding.ActivityMainBinding
import com.broadcast.myapplication.model.FeedTitle
import com.broadcast.myapplication.model.UserPost
import com.broadcast.myapplication.utils.SwipeToDelete
import com.broadcast.myapplication.utils.getRandomFeed
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val titlesList: MutableList<Item> by lazy {
        MutableList(1) { FeedTitle("Актуальное за сегодня:") }
    }
    private val postsList: MutableList<Item> by lazy {
        getRandomFeed(this)
    }

    private val titleAdapter = FingerprintAdapter(listOf(TitleFingerprint()))
    private val postAdapter = FingerprintAdapter(
        listOf(
            PostFingerprint(::onSavePost),
            HorizontalItemsFingerprint(
                listOf(PostFingerprint(::onSavePost)),
                70
            )
        )
    )

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

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = concatAdapter

            addItemDecoration(FeedHorizontalDividerItemDecoration(70, listOf(R.layout.item_horizontal_list))) // addable
            addItemDecoration(GroupVerticalItemDecoration(R.layout.item_post, 100, 0)) // addable
            addItemDecoration(GroupVerticalItemDecoration(R.layout.item_title, 0, 100)) // addable
            addItemDecoration(GroupVerticalItemDecoration(R.layout.item_horizontal_list, 0, 150)) // addable

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

    private fun onSavePost(post: UserPost) {
        val postIndex = postsList.indexOf(post)
        val newItem = post.copy(isSaved = post.isSaved.not())

        postsList.removeAt(postIndex)
        postsList.add(postIndex, newItem)
        postAdapter.submitList(postsList.toList())
    }

    private fun submitInitialListWithDelayForAnimation() {
        binding.recyclerView.postDelayed({
            titleAdapter.submitList(titlesList.toList())
            postAdapter.submitList(postsList.toList())
        }, 300L)
    }

    private fun initSwipeToDelete() {
        val onItemSwipedToDelete = { positionForRemove: Int ->
            val removedItem = postsList[positionForRemove]
            postsList.removeAt(positionForRemove)
            postAdapter.submitList(postsList.toList())

            showRestoreItemSnackbar(positionForRemove, removedItem)

        }
        val swipeToDeleteCallback = SwipeToDelete(onItemSwipedToDelete)
        ItemTouchHelper(swipeToDeleteCallback).attachToRecyclerView(binding.recyclerView)
    }

    private fun showRestoreItemSnackbar(position: Int, item: Item) {
        Snackbar.make(binding.recyclerView, "Item was deleted", Snackbar.LENGTH_LONG)
            .setAction("Undo") {
                postsList.add(position, item)
                postAdapter.submitList(postsList.toList())
            }.show()
    }
}