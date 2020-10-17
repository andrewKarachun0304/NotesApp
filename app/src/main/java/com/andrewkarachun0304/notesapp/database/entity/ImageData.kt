package com.andrewkarachun0304.notesapp.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "image_data")
data class ImageData(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "image_title")
    val title: String,
    @ColumnInfo(name = "image_path")
    val path: String
) : Parcelable