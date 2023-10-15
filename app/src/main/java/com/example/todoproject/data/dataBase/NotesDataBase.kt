package com.example.todoproject.data.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NotesDataBase(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "date")val date: String,
    @ColumnInfo(name = "body")val body: String)