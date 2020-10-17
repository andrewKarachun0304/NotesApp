package com.andrewkarachun0304.notesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrewkarachun0304.notesapp.R
import com.andrewkarachun0304.notesapp.database.entity.Note
import kotlinx.android.synthetic.main.item_notes.view.*


class NotesAdapter(val noteListener: NoteListener) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var notesSet = ArrayList<Note>()
    fun updateList(notes: List<Note>?) {
        notesSet = notes as ArrayList<Note>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_notes, parent, false)
        return NotesViewHolder(view)

    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(notesSet[position])
    }

    override fun getItemCount() = notesSet.size

    inner class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(note: Note){
            with(itemView){
                title_note_tv.text = note.title
                body_note_tv.text = note.body
                setOnClickListener {
                    noteListener.onNoteClick(note)
                }
            }
        }
    }

    interface NoteListener {
        fun onNoteClick(note: Note)
    }

}


