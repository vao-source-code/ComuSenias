package com.example.comusenias.domain.library.chapter

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class ProvideChapter(
    var chapter: String,
    var title: String,
    @DrawableRes var image: Int,
    var color: Color,
    var chapterComplete: Boolean = false,
)