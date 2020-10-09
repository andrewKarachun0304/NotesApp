package com.andrewkarachun0304.notesapp

import android.content.ContentValues
import android.content.Context

class AppDataBase private constructor(context: Context) {
    private val dbHelper by lazy { DbHelper(context) }

    companion object {
        fun getInstance(context: Context) = AppDataBase(context)
    }

    fun addImageToDataBase(imageData: ImageData) {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("title_image", imageData.title)
            put("image_path", imageData.path)
        }

        db.insert("image_table", null, values)
    }

    fun getAllImage() :ArrayList<ImageData>{
        val db = dbHelper.readableDatabase

        val cursor = db.query(
            "image_table",
            null,
            null,
            null,
            null,
            null,
            null
        )

        val imageList = ArrayList<ImageData>()
        with(cursor){
            if (moveToFirst()){
                while (moveToNext()){
                    val id = getInt(getColumnIndexOrThrow("id"))
                    val title = getString(getColumnIndexOrThrow("title_image"))
                    val path = getString(getColumnIndexOrThrow("image_path"))
                    imageList.add(ImageData(id, title, path))
                }
            }
        }
        return imageList
    }
}