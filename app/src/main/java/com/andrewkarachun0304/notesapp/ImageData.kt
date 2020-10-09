package com.andrewkarachun0304.notesapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageData(
    val id: Int,
    val title: String,
    val path: String
) : Parcelable