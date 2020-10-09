package com.andrewkarachun0304.notesapp

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_show_image.*
import java.util.*

private const val IMAGE_DATA_KEY = "image_data"

class ShowImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)

        val data = intent.getParcelableExtra<ImageData>(IMAGE_DATA_KEY)
        if (data != null){
            full_image_iv.setImageBitmap(showImage(data.path))
        }

    }

    private fun showImage(fileName: String): Bitmap {
        val imageStr = ReadWriteImage.readFromFile(this, fileName)
        val bitmap = Base64Converter.base64ToImage(imageStr)
        return bitmap
    }
}