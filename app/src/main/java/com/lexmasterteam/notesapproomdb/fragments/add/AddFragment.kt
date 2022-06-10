package com.lexmasterteam.notesapproomdb.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lexmasterteam.notesapproomdb.R
import com.lexmasterteam.notesapproomdb.databinding.FragmentAddBinding
import com.lexmasterteam.notesapproomdb.model.Note
import com.lexmasterteam.notesapproomdb.viewmodel.NoteViewModel


class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    private lateinit var mNameViewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)

        mNameViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btCommit.setOnClickListener {
            AddDataToDatabase()
        }
    }

    private fun AddDataToDatabase() {

        val title = binding.etTitle.text.toString()
        val description = binding.etDescription.text.toString()

        if(CheckString(title,description)){
            val note = Note(0,title,description)
            mNameViewModel.addNote(note)
            Toast.makeText(requireContext(),"Note was added!",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_mainFragment)
        } else{
            Toast.makeText(requireContext(),"Please fill all fields!",Toast.LENGTH_SHORT).show()
        }

    }
    private fun CheckString(tit:String,desc:String): Boolean{
        return !(TextUtils.isEmpty(tit)&&TextUtils.isEmpty(desc))
    }
}