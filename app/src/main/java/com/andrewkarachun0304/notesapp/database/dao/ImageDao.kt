package com.andrewkarachun0304.notesapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andrewkarachun0304.notesapp.database.entity.ImageData

@Dao
interface ImageDao {
    @Insert
    suspend fun addImage(imageData: ImageData)

    @Query("SELECT * FROM image_data")
    suspend fun getAllImage(): List<ImageData>
}