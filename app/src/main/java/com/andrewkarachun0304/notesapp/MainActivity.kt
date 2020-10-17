package com.andrewkarachun0304.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrewkarachun0304.notesapp.adapter.NotesAdapter
import com.andrewkarachun0304.notesapp.database.AppDataBase
import com.andrewkarachun0304.notesapp.database.entity.Note
import com.andrewkarachun0304.notesapp.utils.launchIO
import com.andrewkarachun0304.notesapp.utils.launchUI
import kotlinx.android.synthetic.main.activity_main.*

private const val NOTE_KEY = "note"
private const val CREATE_NOTE_REQUEST_CODE = 101
private const val UPDATE_NOTE_REQUEST_CODE = 202

class MainActivity : AppCompatActivity() {
    private val noteDao by lazy {
        AppDataBase.getInstance(applicationContext)?.getNoteDao()
    }

    private val adapter by lazy {
        NotesAdapter(object : NotesAdapter.NoteListener {
            override fun onNoteClick(note: Note) {
                val intent = Intent(this@MainActivity, UpdateNoteActivity::class.java)
                intent.putExtra(NOTE_KEY, note)
                startActivityForResult(intent, UPDATE_NOTE_REQUEST_CODE)
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        updateNoteList()

        add_new_note_fab.setOnClickListener {
            val intent = Intent(this, CreateNoteActivity::class.java)
            startActivityForResult(intent, CREATE_NOTE_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            updateNoteList()
        }
    }

    private fun updateNoteList() {
        launchIO {
            val result = noteDao?.getAllNotes()
            launchUI {
                adapter.updateList(result)
            }
        }
    }
}