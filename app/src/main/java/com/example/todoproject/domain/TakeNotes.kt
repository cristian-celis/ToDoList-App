package com.example.todoproject.domain

import com.example.todoproject.data.dataBase.NoteDao
import com.example.todoproject.data.dataBase.NotesDataBase
import javax.inject.Inject

class TakeNotes @Inject constructor(
    private val noteDao: NoteDao
) {

    private var lastId: Int = 0

    suspend fun getAllNotes(): List<Notes> {
        val response: List<NotesDataBase> = noteDao.getAllNotes()
        if(response.isEmpty()){
            return emptyList()
        }
        lastId = response.last().id
        return response.map { it.toDomain() }
    }

    suspend fun deleteNote(idNote: Int) {
        noteDao.deleteNote(idNote)
    }

    suspend fun getNote(idNote: Int): Notes{
        val response: NotesDataBase = noteDao.getNote(idNote)
        return response.toDomain()
    }

    suspend fun saveNote(idNote: Int, titleNote: String, bodyNote: String) {
        if (idNote > -1) {
            noteDao.updateNote(NotesDataBase(idNote, titleNote, "00", bodyNote))
        } else {
            noteDao.add(NotesDataBase(lastId++, titleNote, "00", bodyNote))
        }
    }

    suspend fun searchNote(titleNote: String): List<Notes>{
        getAllNotes().forEach {
            if (it.title == titleNote){
                return listOf(it)
            }
        }
        return emptyList()
    }
}