package com.example.todoproject.Presentation.view.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.todoproject.databinding.ForItemsBinding
import com.example.todoproject.domain.Notes

class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ForItemsBinding.bind(view)

    fun setInfoNotes(notesDataBase: Notes){
        binding.titleNoteTxt.text = notesDataBase.title
        binding.dateNote.text = notesDataBase.date
        binding.bodyNote.text = notesDataBase.body
    }
}
