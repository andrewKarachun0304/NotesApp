package com.andrewkarachun0304.notesapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.andrewkarachun0304.notesapp.adapter.GalleryAdapter
import kotlinx.android.synthetic.main.activity_galery.*
import kotlinx.android.synthetic.main.activity_main.*

const val GALLERY_REQUEST_CODE = 101

class GaleryActivity : AppCompatActivity() {
    private val dataBase by lazy { AppDataBase.getInstance(this) }
    private val galleryAdapter by lazy { GalleryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galery)

        recycler_view.apply {
            adapter = galleryAdapter
            layoutManager = GridLayoutManager(this@GaleryActivity, 2)
        }
        updateList()


        floating_action_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, GALLERY_REQUEST_CODE)
        }
    }

    private fun updateList(){
        galleryAdapter.updateDataGallery(dataBase.getAllImage())
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
                updateList()
            }
        }
    }

    private fun Uri.getImageName() = this
        .toString()
        .substringAfterLast("%2")
        .substringBeforeLast('.')
        .trim()
}