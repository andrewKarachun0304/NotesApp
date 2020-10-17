package com.andrewkarachun0304.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andrewkarachun0304.notesapp.database.dao.ImageDao
import com.andrewkarachun0304.notesapp.database.dao.NoteDao
import com.andrewkarachun0304.notesapp.database.entity.ImageData
import com.andrewkarachun0304.notesapp.database.entity.Note

@Database(entities = [ImageData::class, Note::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getImageDao(): ImageDao
    abstract fun getNoteDao(): NoteDao


    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase? {
            if (INSTANCE == null) {
                synchronized(AppDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AppDataBase::class.java,
                        "my_database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }


}