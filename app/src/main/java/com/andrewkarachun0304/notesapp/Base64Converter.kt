package com.andrewkarachun0304.notesapp

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import java.io.ByteArrayOutputStream
import java.util.*

object Base64Converter {
    fun imageToBase64(contentResolver: ContentResolver, selectedImage: Uri): String{
        val imageStream = contentResolver.openInputStream(selectedImage)
        val bitmap = BitmapFactory.decodeStream(imageStream)
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageStr = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Base64.getEncoder().encodeToString(baos.toByteArray())
        } else {
            TODO()
        }
        return imageStr
    }

    fun base64ToImage(text: String): Bitmap{
        val image = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Base64.getDecoder().decode(text)
        } else {
            TODO()
        }
        return BitmapFactory.decodeByteArray(image, 0, image.size)
    }
}