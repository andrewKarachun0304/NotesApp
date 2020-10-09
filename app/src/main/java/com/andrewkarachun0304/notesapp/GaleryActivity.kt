package com.andrewkarachun0304.notesapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_galery.*

const val GALLERY_REQUEST_CODE = 101

class GaleryActivity : AppCompatActivity() {
    private val dataBase by lazy { AppDataBase.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galery)

        floating_action_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, GALLERY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageData: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageData)

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {
            val selectedImage = imageData?.data
            if (selectedImage != null) {
                val fileName = selectedImage.getImageName()
                val imageStr = Base64Converter.imageToBase64(contentResolver, selectedImage)
                ReadWriteImage.writeToFile(this, imageStr, fileName)
                dataBase.addImageToDataBase(ImageData(0, "title", fileName))
            }
        }
    }

    private fun Uri.getImageName() = this
        .toString()
        .substringAfterLast("%2")
        .substringBeforeLast('.')
        .trim()
}