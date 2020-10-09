package com.andrewkarachun0304.notesapp

import android.content.Context
import java.io.FileInputStream
import java.io.FileOutputStream

object ReadWriteImage {
    fun readFromFile(context: Context, fileName: String): String {
        val fis: FileInputStream = context.openFileInput(fileName)
        val text = fis.readBytes().toString(Charsets.UTF_8)
        fis.close()
        return text
    }

    fun writeToFile(context: Context, image: String, path: String){
        val fos: FileOutputStream = context.openFileOutput(path, Context.MODE_PRIVATE)
        fos.write(image.toByteArray())
        fos.close()
    }
}