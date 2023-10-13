package com.example.todoproject.Data.daggerHilt

import android.content.Context
import androidx.room.Room
import com.example.todoproject.Data.dataBase.NoteEntity
import com.example.todoproject.domain.TakeNotes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    const val DATABASE_NAME = "note_database"

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, NoteEntity::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideDao(db: NoteEntity) = db.notaDao()
}