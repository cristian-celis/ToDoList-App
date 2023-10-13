package com.example.todoproject.Presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.example.todoproject.Presentation.viewmodel.UpdateNotesViewModel
import com.example.todoproject.R
import com.example.todoproject.databinding.FragmentSecondBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment(private var idClicked: Int) : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    private val updateNotesViewModel: UpdateNotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun fillInfoAboutNote() {
        if (idClicked != -1) {
            updateNotesViewModel.note.observe(viewLifecycleOwner) {
                it?.let {
                    binding.titleNoteTxt.setText(it.title)
                    binding.bodyNoteTxt.setText(it.body)
                }
            }
            updateNotesViewModel.changeNote(idClicked)
        } else {
            binding.bodyNoteTxt.setText("")
            binding.titleNoteTxt.setText("")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(layoutInflater)
        fillInfoAboutNote()
        goBackBttListener()
        saveNoteBttListener()
        deleteNoteBttListener()

        return binding.root
    }

    private fun deleteNoteBttListener(){
        binding.deleteNoteBtt.setOnClickListener{
            if(textBoxesAreClean() && idClicked != -1){
                AlertDialog.Builder(requireContext())
                    .setTitle("Confirm Action")
                    .setMessage("Are you sure to delete this note?")
                    .setPositiveButton("Yes sir") { dialog, which ->
                        updateNotesViewModel.deleteNote(idClicked)
                        goBackToMainFragment()
                    }
                    .setNegativeButton("Nope") { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
            }else{
                Toast.makeText(context, "There isn't note to delete", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun textBoxesAreClean(): Boolean{
        return binding.bodyNoteTxt.toString().isNotEmpty() && binding.titleNoteTxt.toString().isNotEmpty()
    }

    private fun saveNoteBttListener() {
        binding.saveNoteBtt.setOnClickListener {
            if (textBoxesAreClean()) {
                updateNotesViewModel.saveNote(
                    idClicked,
                    binding.titleNoteTxt.text.toString(),
                    binding.bodyNoteTxt.text.toString()
                )
                Toast.makeText(context, "Note was saved", Toast.LENGTH_SHORT).show()
                goBackToMainFragment()
            } else {
                Toast.makeText(context, "You must fill in all the text boxes", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun goBackToMainFragment() {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.frameContainer, MainFragment())
            addToBackStack(null)
            commit()
        }
    }

    private fun goBackBttListener() {
        binding.goBackBtt.setOnClickListener {
            goBackToMainFragment()
        }
    }
}