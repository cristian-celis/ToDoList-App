package com.example.todoproject.presentation.view.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoproject.R
import com.example.todoproject.databinding.FragmentMainBinding
import com.example.todoproject.presentation.view.recyclerView.NotesAdapter
import com.example.todoproject.presentation.viewmodel.UpdateNotesViewModel
import com.example.todoproject.presentation.core.listIsEmpty
import com.example.todoproject.presentation.core.listIsNotEmpty
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
        goBackButton()
        initializeSearchComponent()

        return binding.root
    }

    private fun initializeSearchComponent() {
        binding.searchViewTxt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.contains("\n")) {
                    searchNote(s.toString().substringBefore("\n"))
                }
            }
        })
    }

    private fun searchNote(writtenText: String) {
        binding.searchViewTxt.setText("")
        hideKeyboard()
        updateNotesViewModel.updateSearchList(writtenText)
    }

    private fun initializeRecyclerView() {
        var notesAdapter: NotesAdapter

        recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(activity)

        updateNotesViewModel.notesDataBaseModel.observe(viewLifecycleOwner) {
            Log.d("prueba", "Its empty: ${it.size}")
            if (it.isEmpty()){
                binding.listIsEmpty()
            }else{
                binding.listIsNotEmpty()
            }
            notesAdapter = NotesAdapter(it)

            notesAdapter.setOnClickListener(object : NotesAdapter.OnClickListener {
                override fun onClick(position: Int, model: Notes) {
                    goToAddNoteFragment(model.id)
                }
            })
            recyclerView.adapter = notesAdapter
        }
        updateNotesViewModel.updateList()
    }

    private fun goBackButton(){
        binding.goBackBtt.setOnClickListener {
            updateNotesViewModel.updateList()
        }
    }

    private fun goToAddNoteFragment(itemClicked: Int) {
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

    private fun hideKeyboard(){
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}