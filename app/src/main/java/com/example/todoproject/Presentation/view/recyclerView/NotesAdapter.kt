package com.example.todoproject.Presentation.view.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoproject.R
import com.example.todoproject.domain.Notes

class NotesAdapter(private val notes: List<Notes>): RecyclerView.Adapter<NoteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.for_items,
            parent,
            false
        )
        return NoteViewHolder(view)
    }

    private var onClickListener: OnClickListener? = null

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]

        holder.setInfoNotes(Notes(note.id, note.title, note.date, note.body))

        holder.itemView.setOnClickListener{
            if (onClickListener != null){
                onClickListener!!.onClick(position, note)
            }
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }

    interface OnClickListener{
        fun onClick(position: Int, model: Notes)
    }
}
