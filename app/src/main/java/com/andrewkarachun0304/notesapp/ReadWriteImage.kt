package com.andrewkarachun0304.notesapp

import android.content.Context
import java.io.*

object ReadWriteImage {
    fun readFromFile(context: Context, fileName: String): String {
        val br = BufferedReader(InputStreamReader(context.openFileInput(fileName)))
        val text = br.readText()
        br.close()
        return text
    }

    fun writeToFile(context: Context, image: String, path: String) {
        val bw = BufferedWriter(OutputStreamWriter(context.openFileOutput(path, Context.MODE_PRIVATE)))
        bw.write(image)
        bw.close()
    }
}