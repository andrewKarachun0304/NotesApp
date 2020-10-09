package com.andrewkarachun0304.notesapp.dto

data class Notes(
    val note:String
)
object notesDataBase{
    val notesDB = mutableListOf<Notes>()

    fun addNote(note: String){
        notesDB.add(Notes(note))
    }

    fun filingTheBase(){
        addNote("купить кофе")
        addNote("купить молоко")
    }

}