package com.groupnine.tebaktajwid.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quiz(
    var title: String?,
    var level: String?,
    var icon: Int?,
    var question: Int?,
    var audio: MutableList<Int>?,
    var answer: String?
) : Parcelable
