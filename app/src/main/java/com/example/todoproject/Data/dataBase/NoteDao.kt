package com.example.todoproject.Data.dataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(note: NotesDataBase)

    @Update
    suspend fun updateNote(note: NotesDataBase)

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<NotesDataBase>

    @Query("SELECT * FROM notes WHERE id = :idNote")
    suspend fun getNote(idNote: Int): NotesDataBase

    @Query("DELETE  FROM notes WHERE id = :idNote")
    suspend fun deleteNote(idNote: Int)

    @Query("DELETE FROM notes")
    suspend fun deleteAll()
}