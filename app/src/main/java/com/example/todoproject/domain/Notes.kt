package com.example.todoproject.domain

import com.example.todoproject.data.dataBase.NotesDataBase

data class Notes(
    val id: Int = 0,
    val title: String,
    val date: String,
    val body: String
)

fun NotesDataBase.toDomain() = Notes(id, title, date, body)