package com.andrewkarachun0304.notesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrewkarachun0304.notesapp.R
import com.andrewkarachun0304.notesapp.dto.Notes
import kotlinx.android.synthetic.main.item_notes.view.*


class NotesAdapter (private val notesSet:List<Notes>):
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_notes,parent,false)
        return NotesViewHolder(view)

    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.itemView.textNote.text = notesSet[position].note
    }

    override fun getItemCount(): Int {
        return notesSet.size
    }
}


