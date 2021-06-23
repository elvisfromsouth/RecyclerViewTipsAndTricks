package com.broadcast.myapplication.model

import android.os.Parcelable
import com.broadcast.myapplication.adapter.Item

data class HorizontalItems(
    val items: List<Item>,
    var state: Parcelable? = null
) : Item