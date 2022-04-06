package com.rizkyhamdana.janitraapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryEntity (
    val name: String,
    val image: String

    ):Parcelable