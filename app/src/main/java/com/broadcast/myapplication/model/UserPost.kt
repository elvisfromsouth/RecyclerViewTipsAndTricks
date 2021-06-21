package com.broadcast.myapplication.model

import androidx.annotation.DrawableRes

data class UserPost(
    val postId: Long,
    val userNickname: String,
    val text: String,
    val likesCount: Int,
    val commentsCount: Int,
    @DrawableRes
    val imageResId: Int,
    val isSaved: Boolean
)