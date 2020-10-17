package com.andrewkarachun0304.notesapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andrewkarachun0304.notesapp.database.entity.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM note_table")
    suspend fun getAllNotes(): List<Note>

    @Query("DELETE FROM note_table WHERE id = :id")
    suspend fun deleteNoteById(id: Int?)
}