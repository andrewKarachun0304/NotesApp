package com.andrewkarachun0304.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add_note_button.setOnClickListener{
            val intent = Intent(this,CreateNoteActivity::class.java)
            startActivity(intent)
        }
    }
}