package com.example.todoproject.Data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NotesDataBase::class], version = 1)
abstract class NoteEntity: RoomDatabase() {

    abstract fun notaDao(): NoteDao
}