package com.broadcast.myapplication.utils

import com.broadcast.myapplication.R
import com.broadcast.myapplication.model.UserPost
import kotlin.random.Random


fun getRandomUserPost() = UserPost(
    postId = Random.nextLong(),
    userNickname = "User#${Random.nextInt()}",
    text = commentsSamples.random(),
    likesCount = Random.nextInt(0, 9999),
    commentsCount = Random.nextInt(0, 9999),
    imageResId = imagesIds.random(),
    isSaved = false,
)

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