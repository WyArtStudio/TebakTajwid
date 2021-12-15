package com.groupnine.tebaktajwid.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Material(
    var icon: Int?,
    var title: String?,
    var description: String?,
    var content: String?
) : Parcelable
