package com.andrewkarachun0304.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andrewkarachun0304.notesapp.database.AppDataBase
import com.andrewkarachun0304.notesapp.database.entity.Note
import com.andrewkarachun0304.notesapp.utils.launchIO
import kotlinx.android.synthetic.main.activity_update_note.*

private const val NOTE_KEY = "note"
class UpdateNoteActivity : AppCompatActivity() {
    private val noteDao by lazy {
        AppDataBase.getInstance(applicationContext)?.getNoteDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_note)

        val note = intent.getParcelableExtra(NOTE_KEY) ?: Note(title = "", body = "")

        title_update_note_et.setText(note.title)
        body_update_note_et.setText(note.body)
        val id = note.id

        delete_note_btn.setOnClickListener {
            launchIO {
                noteDao?.deleteNoteById(note.id)
            }
            setResult(RESULT_OK)
            finish()
        }
    }
}