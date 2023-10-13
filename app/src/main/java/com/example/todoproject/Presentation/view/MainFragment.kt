package com.example.todoproject.Presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoproject.Data.dataBase.NotesDataBase
import com.example.todoproject.R
import com.example.todoproject.databinding.FragmentMainBinding
import com.example.todoproject.Presentation.view.recyclerView.NotesAdapter
import com.example.todoproject.Presentation.viewmodel.UpdateNotesViewModel
import com.example.todoproject.domain.Notes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var recyclerView: RecyclerView

    private val updateNotesViewModel: UpdateNotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)

        initializeRecyclerView()
        setAddNoteBttListener()

        return binding.root
    }

    private fun initializeRecyclerView(){
        var notesAdapter: NotesAdapter

        recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(activity)

        updateNotesViewModel.notesDataBaseModel.observe(viewLifecycleOwner) {
            notesAdapter = NotesAdapter(it)

            notesAdapter.setOnClickListener(object : NotesAdapter.OnClickListener{
                override fun onClick(position: Int, model: Notes) {
                    goToAddNoteFragment(model.id)
                }
            })

            recyclerView.adapter = notesAdapter
        }
        updateNotesViewModel.updateList()
    }

    private fun goToAddNoteFragment(itemClicked: Int){
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.frameContainer, SecondFragment(itemClicked))
            addToBackStack(null)
            commit()
        }
    }

    private fun setAddNoteBttListener() {
        binding.addNote.setOnClickListener {
            goToAddNoteFragment(-1)
        }
    }
}