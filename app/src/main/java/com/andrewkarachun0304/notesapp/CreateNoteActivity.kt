package com.andrewkarachun0304.notesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.andrewkarachun0304.notesapp.database.AppDataBase
import com.andrewkarachun0304.notesapp.database.entity.Note
import com.andrewkarachun0304.notesapp.utils.launchIO
import kotlinx.android.synthetic.main.activity_create_note.*

private const val KEY_PASS = "12345"

class CreateNoteActivity : AppCompatActivity() {
    private val noteDao by lazy {
        AppDataBase.getInstance(applicationContext)?.getNoteDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        create_new_note_button.setOnClickListener {
            if (title_new_note_et.text.toString() == KEY_PASS) {
                val intent = Intent(this, GalleryActivity::class.java)
                startActivity(intent)
            } else {
                if (title_new_note_et.text.toString().isNotEmpty()
                    && body_new_note_et.text.toString().isNotEmpty()
                ) {
                    addNote()

                } else {
                    Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun addNote() {
        val title = title_new_note_et.text.toString()
        val body = body_new_note_et.text.toString()
        val note = Note(title = title, body = body)
        launchIO {
            noteDao?.addNote(note)
        }
    }

}