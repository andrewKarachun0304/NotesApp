package com.andrewkarachun0304.notesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_note.*

const val KEY_PASS = "12345"

class CreateNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        // переход на экран со скрытой галереей при вводе KEY_PASS
        create_new_note_button.setOnClickListener {
            if (create_new_note_text_view.text.toString() == KEY_PASS) {
                intent = Intent(this, GaleryActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "ERROR 403! x0F28182001", Toast.LENGTH_SHORT).show()
            }
        }

    }
}