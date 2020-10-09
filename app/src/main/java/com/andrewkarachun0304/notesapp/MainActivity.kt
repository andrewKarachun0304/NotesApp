package com.andrewkarachun0304.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrewkarachun0304.notesapp.adapter.NotesAdapter
import com.andrewkarachun0304.notesapp.dto.notesDataBase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesDataBase.filingTheBase()
        val adapter = NotesAdapter(notesDataBase.notesDB)

        notesRecycler.adapter = adapter
        notesRecycler.layoutManager = LinearLayoutManager(this)
        notesRecycler.hasFixedSize()

        add_note_button.setOnClickListener{
            val intent = Intent(this,CreateNoteActivity::class.java)
            startActivity(intent)
        }
    }
}