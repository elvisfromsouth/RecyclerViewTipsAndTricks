package com.broadcast.myapplication.utils

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.core.content.ContextCompat
import com.broadcast.myapplication.R
import com.broadcast.myapplication.model.HorizontalItems
import com.broadcast.myapplication.model.UserPost
import kotlin.random.Random

fun getRandomFeed(context: Context) = MutableList(20) {
    when (it) {
        1, 6, 7, 8, 14, 15 -> getRandomHorizontalItems(context)
        else -> getRandomUserPost(context)
    }
}

fun getRandomHorizontalItems(context: Context) = HorizontalItems(
    List(10) { getRandomUserPost(context) }
)

fun getRandomUserPost(context: Context) = UserPost(
    postId = Random.nextLong(),
    mainComment = getPostDescription("User#${Random.nextInt()}", commentsSamples.random()),
    likesCount = Random.nextInt(0, 9999).toString(),
    commentsCount = Random.nextInt(0, 9999).toString(),
    image = ContextCompat.getDrawable(context, imagesIds.random())!!,
    isSaved = false,
)

private fun getPostDescription(nickName: String, comment: String) =
    SpannableStringBuilder("$nickName: $comment").apply {
        setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            nickName.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
    }

private val commentsSamples = listOf(
    "Идейные соображения высшего порядка, а также дальнейшее развитие различных форм деятельности требуют определения и уточнения соответствующий условий активизации.",
    "Не следует, однако забывать, что дальнейшее развитие различных форм деятельности требуют определения и уточнения соответствующий условий активизации.",
    "Таким образом рамки и место обучения кадров позволяет выполнять важные задания по разработке систем массового участия.",
    "Таким образом, существующая теория позволяет выполнить важные задания по разработке кластеризации усилий.",
    "В своём стремлении повысить качество жизни, они забывают, что постоянный количественный рост и сфера нашей активности является качественно новой ступенью соответствующих условий активизации.",
    "Являясь всего лишь частью общей картины, многие известные личности ограничены исключительно образом мышления.",
    "Также как убеждённость некоторых оппонентов предоставляет широкие возможности для направлений прогрессивного развития.",
    "С другой стороны, глубокий уровень погружения требует анализа системы обучения кадров, соответствующей насущным потребностям."
)

private val imagesIds = listOf(
    R.drawable.img_coast,
    R.drawable.img_detroit,
    R.drawable.img_egret,
    R.drawable.img_girl,
    R.drawable.img_praha,
)