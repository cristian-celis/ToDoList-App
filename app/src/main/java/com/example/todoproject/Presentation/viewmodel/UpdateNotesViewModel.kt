package com.example.todoproject.Presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoproject.domain.TakeNotes
import com.example.todoproject.Data.dataBase.NotesDataBase
import com.example.todoproject.domain.Notes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UpdateNotesViewModel @Inject constructor(
    private val takeNotes: TakeNotes
) : ViewModel() {

    val notesDataBaseModel = MutableLiveData<List<Notes>>()

    var note = MutableLiveData<Notes>()

    fun updateList() {
        viewModelScope.launch {
            notesDataBaseModel.postValue(takeNotes.getAllNotes())
        }
    }

    fun changeNote(id: Int) {
        viewModelScope.launch {
            note.postValue(takeNotes.getNote(id))
        }
    }

    fun saveNote(idNote: Int, titleNote: String, bodyNote: String) {
        viewModelScope.launch {
            takeNotes.saveNote(idNote, titleNote, bodyNote)
            updateList()
        }
    }

    fun deleteNote(idNote: Int) {
        viewModelScope.launch {
            takeNotes.deleteNote(idNote)
            updateList()
        }
    }
}