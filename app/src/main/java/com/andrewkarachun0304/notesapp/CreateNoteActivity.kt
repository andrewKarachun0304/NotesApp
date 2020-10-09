package com.andrewkarachun0304.notesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.andrewkarachun0304.notesapp.dto.notesDataBase
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
                val text = create_new_note_text_view.text.toString()
                if(text.isNotEmpty()){
                    notesDataBase.addNote(text)
                    Toast.makeText(this,"note $text is added",Toast.LENGTH_SHORT).show()
                }
                if(text.isNullOrEmpty()){
                    Toast.makeText(this,"note is empty... enter text",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}