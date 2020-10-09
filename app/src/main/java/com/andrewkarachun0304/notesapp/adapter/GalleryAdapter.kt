package com.andrewkarachun0304.notesapp.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrewkarachun0304.notesapp.Base64Converter
import com.andrewkarachun0304.notesapp.ImageData
import com.andrewkarachun0304.notesapp.R
import com.andrewkarachun0304.notesapp.ReadWriteImage
import kotlinx.android.synthetic.main.item_photo.view.*

class GalleryAdapter :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    private var gallerySet =  ArrayList<ImageData>()

    fun updateDataGallery(list: ArrayList<ImageData>) {
        gallerySet = list
        notifyDataSetChanged()
    }

    class GalleryViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return GalleryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gallerySet.size
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val imageSrc =
            ReadWriteImage.readFromFile(holder.itemView.context, gallerySet[position].path)
        val bitmap = Base64Converter.base64ToImage(imageSrc)
        holder.itemView.item_photo_image_view.setImageBitmap(
            Bitmap.createScaledBitmap(
                bitmap,
                bitmap.width / 10,
                bitmap.height / 10,
                true
            )
        )
    }
}