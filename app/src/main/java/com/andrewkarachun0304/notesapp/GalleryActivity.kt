package com.andrewkarachun0304.notesapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.andrewkarachun0304.notesapp.adapter.GalleryAdapter
import com.andrewkarachun0304.notesapp.database.AppDataBase
import com.andrewkarachun0304.notesapp.database.entity.ImageData
import com.andrewkarachun0304.notesapp.utils.launchIO
import com.andrewkarachun0304.notesapp.utils.launchUI
import kotlinx.android.synthetic.main.activity_galery.*

const val GALLERY_REQUEST_CODE = 101
private const val IMAGE_DATA_KEY = "image_data"

class GalleryActivity : AppCompatActivity() {
    private val imageDao by lazy { AppDataBase.getInstance(applicationContext)?.getImageDao() }
    private val galleryAdapter by lazy {
        GalleryAdapter(object : GalleryAdapter.Listener {
            override fun onClick(imageData: ImageData) {
                intent = Intent(this@GalleryActivity, ShowImageActivity::class.java)
                intent.putExtra(IMAGE_DATA_KEY, imageData)
                startActivity(intent)
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galery)

        recycler_view.apply {
            adapter = galleryAdapter
            layoutManager = GridLayoutManager(this@GalleryActivity, 2)
        }
        updateList()


        floating_action_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, GALLERY_REQUEST_CODE)
        }
    }

    private fun updateList() {
        launchIO {
            launchUI {
                galleryAdapter.updateDataGallery(imageDao?.getAllImage())
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageData: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageData)

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && imageData?.data != null) {
            val selectedImage = imageData.data
            if (selectedImage != null) {
                val fileName = selectedImage.getImageName().replace('/', '_')

                val imageStr = Base64Converter.imageToBase64(contentResolver, selectedImage)
                ReadWriteImage.writeToFile(this, imageStr, fileName)
                launchIO {
                    imageDao?.addImage(ImageData( title = "title", path = fileName))
                }
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