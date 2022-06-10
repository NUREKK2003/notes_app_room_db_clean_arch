package com.lexmasterteam.notesapproomdb.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lexmasterteam.notesapproomdb.R
import com.lexmasterteam.notesapproomdb.databinding.FragmentUpdateBinding
import com.lexmasterteam.notesapproomdb.model.Note
import com.lexmasterteam.notesapproomdb.viewmodel.NoteViewModel


class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding

    private val  args by navArgs<UpdateFragmentArgs>() //odbiór przesyłanych argumentów

    private lateinit var mNoteViewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(layoutInflater, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.etUpdateTitle.setText(args.selectedNote.title)
        binding.etUpdateDescription.setText(args.selectedNote.description)

        setHasOptionsMenu(true)

        binding.btUpdate.setOnClickListener {
            updateItem()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu) // rozdmuchanie menu
    }

    // obsługa kliknięć w menu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
            DeleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun DeleteUser() {
        var builder = AlertDialog.Builder(requireContext()) // tworzenie okna dialogowego z potwierdzeniem
        builder.setPositiveButton("Yes"){ _, _-> // anonymus argument
            mNoteViewModel.deleteNote(args.selectedNote)
            Toast.makeText(requireContext(),
            "Succesfully removed: ${args.selectedNote.title}",
            Toast.LENGTH_SHORT).show()
            val action =UpdateFragmentDirections.actionUpdateFragmentToMainFragment()
            findNavController().navigate(action)
        }
        builder.setNegativeButton("No"){ _, _->

        }
        builder.setTitle("Delete ${args.selectedNote.title}")
        builder.setMessage("Are you sure you want to delete ${args.selectedNote.title}?")
        builder.create().show()
    }

    private fun updateItem() {
        val title = binding.etUpdateTitle.text.toString()
        val description = binding.etUpdateDescription.text.toString()
        if (CheckString(title,description)){
            val note =Note(args.selectedNote.id,title,description)
            mNoteViewModel.updateNote(note)
            Toast.makeText(requireContext(),"Note updated succesfully!",Toast.LENGTH_SHORT).show()
            val action = UpdateFragmentDirections.actionUpdateFragmentToMainFragment()
            findNavController().navigate(action)
        } else{
            Toast.makeText(requireContext(),"Please, fill all rows!",Toast.LENGTH_SHORT).show()
        }
    }
    private fun CheckString(tit:String,desc:String): Boolean{
        return !(TextUtils.isEmpty(tit)&& TextUtils.isEmpty(desc))
    }
}